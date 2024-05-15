package com.shopApp.service;

import com.shopApp.DTO.mapper.UserMapper;
import com.shopApp.DTO.response.UserDTO;
import com.shopApp.entity.User;
import com.shopApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDTO> getAllUserDTOs() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserDTOById(Long id) {
        return userRepository.findById(id).map(userMapper::toDTO);
    }

    public UserDTO saveUserDTO(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(Long id, UserDTO newUserDTO) {
        User newUser = userMapper.toEntity(newUserDTO);
        newUser.setId(id);
        return userMapper.toDTO(userRepository.save(newUser));
    }
}
