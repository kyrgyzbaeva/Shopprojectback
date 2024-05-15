package kg.edu.alatoo.online.shop.controller;

import kg.edu.alatoo.online.shop.dto.JwtResponse;
import kg.edu.alatoo.online.shop.dto.LoginRequest;
import kg.edu.alatoo.online.shop.dto.SignupRequest;
import kg.edu.alatoo.online.shop.dto.TokenRefreshRequest;
import kg.edu.alatoo.online.shop.security.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return new ResponseEntity<>("Подтвердите вашу почту по ссылке отправленной на вашу почту", HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody TokenRefreshRequest tokenRefreshRequest) {
        return new ResponseEntity<>(authService.refreshToken(tokenRefreshRequest), HttpStatus.OK);
    }

    @GetMapping("/confirm-account")
    public String confirmUserAccount(@RequestParam("token") String confirmationToken) {
        return authService.confirmEmail(confirmationToken);
    }

    @DeleteMapping("/logout/{userId}")
    public ResponseEntity<?> logout(@NotNull @PathVariable Long userId) {
        authService.deleteRefreshToken(userId);
        return ResponseEntity.ok("Сеанс завершен");
    }
}
