package com.mobilebg.service;

import com.mobilebg.model.dto.UserLoginDTO;
import com.mobilebg.model.entity.UserEntity;
import com.mobilebg.repository.UserRepository;
import com.mobilebg.user.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUser currentUser;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, CurrentUser currentUser, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.passwordEncoder = passwordEncoder;
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
