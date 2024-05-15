package kg.edu.alatoo.online.shop.security.service;

import kg.edu.alatoo.online.shop.model.*;
import kg.edu.alatoo.online.shop.exception.EmailNotFoundException;
import kg.edu.alatoo.online.shop.exception.RefreshTokenNotFoundException;
import kg.edu.alatoo.online.shop.exception.RoleNotFoundException;
import kg.edu.alatoo.online.shop.mapper.UserMapper;
import kg.edu.alatoo.online.shop.dto.*;
import kg.edu.alatoo.online.shop.repository.ConfirmationTokenRepository;
import kg.edu.alatoo.online.shop.repository.RoleRepository;
import kg.edu.alatoo.online.shop.repository.UserRepository;
import kg.edu.alatoo.online.shop.security.jwt.JwtUtils;
import kg.edu.alatoo.online.shop.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmailService emailService;

    private final UserMapper userMapper;


    @Autowired
    AuthenticationManager authenticationManager;

    private final RefreshTokenService refreshTokenService;

    private final ConfirmationTokenRepository confirmationTokenRepository;

    private final UserRepository userRepository;

    public AuthService(UserMapper userMapper, RefreshTokenService refreshTokenService, ConfirmationTokenRepository confirmationTokenRepository, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.refreshTokenService = refreshTokenService;
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.userRepository = userRepository;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();

        String jwt = jwtUtils.generateJwtToken(user);

        List<String> roles = user.getAuthorities().stream().map(item -> item.getAuthority())
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getUserId());

        return new JwtResponse(jwt,
                refreshToken.getToken(),
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                roles);
    }

    public TokenRefreshResponse refreshToken(TokenRefreshRequest tokenRefreshRequest) {

        String requestRefreshToken = tokenRefreshRequest.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return new TokenRefreshResponse(token, requestRefreshToken);
                })
                .orElseThrow(() -> new RefreshTokenNotFoundException(requestRefreshToken,
                        "Токен не найден"));
    }

    public void registerUser(SignupRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameNotFoundException("Такое имя пользователя уже существует");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new EmailNotFoundException("Такая почта уже существует");
        }


        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(Roles.ROLE_ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException("Роль не найдена"));
                        roles.add(adminRole);
                    }
                    case "developer" -> {
                        Role modRole = roleRepository.findByName(Roles.ROLE_SELLER)
                                .orElseThrow(() -> new RoleNotFoundException("Роль не найдена"));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(Roles.ROLE_USER)
                                .orElseThrow(() -> new RoleNotFoundException("Роль не найдена"));
                        roles.add(userRole);
                    }
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);

        sendConfirmationEmail(user, confirmationToken);
    }

    public String confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return "Ваша почта подтверждена успешно!";
        }
        return "Ошибка: не удалось верифицировать вашу почту";
    }


    public void deleteRefreshToken(Long userId) {
        refreshTokenService.deleteByUserId(userId);
    }

    private void sendConfirmationEmail(User user, ConfirmationToken confirmationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("Andrei");
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Завершите регестрацию!");
        mailMessage.setText("Для подтверждения вашего аккаунта пройдите по ссылке ниже : "
                + "http://localhost:8080/api/auth/confirm-account?token=" + confirmationToken.getConfirmationToken());

        emailService.sendEmail(mailMessage);
    }
}
