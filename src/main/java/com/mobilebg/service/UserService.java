package com.mobilebg.service;

import com.mobilebg.model.dto.UserLoginDTO;
import com.mobilebg.model.dto.UserRegisterDTO;
import com.mobilebg.model.entity.UserEntity;
import com.mobilebg.model.entity.UserRoleEntity;
import com.mobilebg.model.mapper.UserMapper;
import com.mobilebg.repository.UserRepository;
import com.mobilebg.user.CurrentUser;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = UserMapper.INSTANCE;;
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {
        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        newUser.setActive(true);
//        UserEntity newUser = UserEntity.builder()
//                .email(userRegisterDTO.getEmail())
//                .password(passwordEncoder.encode(userRegisterDTO.getPassword()))
//                .firstName(userRegisterDTO.getFirstName())
//                .lastName(userRegisterDTO.getLastName())
//                .isActive(true)
//                .build();
        newUser = this.userRepository.save(newUser);
        login(newUser);
    }

    public boolean login(UserLoginDTO loginDTO) {
        Optional<UserEntity> userOpt = this.userRepository.findByEmail(loginDTO.getUsername());
        if (userOpt.isEmpty()) {
            log.info("User with name [{}] not found!", loginDTO.getUsername());
            return false;
        }

        String rawPassword = loginDTO.getPassword();
        String encodedPassword = userOpt.get().getPassword();
        boolean success = passwordEncoder.matches(rawPassword, encodedPassword);
        if (success) {
            login(userOpt.get());
        } else {
            logout();
        }

        return success;
    }

    private void login(UserEntity userEntity) {
        currentUser.setLoggedIn(true);
        currentUser.setName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }

    public void logout() {
        currentUser.clear();
    }

}
