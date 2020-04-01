package com.userslivecoding.usersManagement.application.user;

import com.userslivecoding.usersManagement.domain.user.User;
import com.userslivecoding.usersManagement.domain.user.UserDTO;
import com.userslivecoding.usersManagement.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCreator {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(UserCreator.class);

    public UserCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO exec(UserDTO userToCreate) {
        if (doUserExist(userToCreate)) {
            String error = String.format("User %s with age %s allready exists",
                    userToCreate.getName(), userToCreate.getAge());
            logger.error(error);
            throw new RuntimeException(error);
        }
        User user = new User(-1, userToCreate.getName(), userToCreate.getAge());
        User createdUser = userRepository.save(user);
        return new UserDTO(createdUser.getName(), createdUser.getAge());

    }

    private boolean doUserExist(UserDTO userDTO) {
        Optional<User> user = userRepository.findByNameAndAge(userDTO.getName(), userDTO.getAge());
        return user.isPresent();
    }

}
