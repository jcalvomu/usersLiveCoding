package com.userslivecoding.usersManagement.infrastructure.rest;

import com.userslivecoding.usersManagement.application.user.UserCreator;
import com.userslivecoding.usersManagement.application.user.UserFinder;
import com.userslivecoding.usersManagement.application.user.UserProvider;
import com.userslivecoding.usersManagement.domain.user.UserDTO;
import com.userslivecoding.usersManagement.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        logger.info("Trying to get all users ");
        UserProvider userProvider = new UserProvider(userRepository);
        return userProvider.exec();
    }

    @GetMapping("{userName}")
    public List<UserDTO> getOneUser(@PathVariable String userName) {
        logger.debug("Trying to get one user {}", userName);
        UserFinder userFinder = new UserFinder(userRepository);
        return userFinder.exec(userName);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userToCreate) {
        logger.info("Trying to create user {}", userToCreate);
        UserCreator userCreator = new UserCreator(userRepository);
        return userCreator.exec(userToCreate);
    }
}
