package com.userslivecoding.usersManagement.infrastructure.rest;

import com.userslivecoding.usersManagement.application.user.UserCreator;
import com.userslivecoding.usersManagement.application.user.UserFinder;
import com.userslivecoding.usersManagement.application.user.UserProvider;
import com.userslivecoding.usersManagement.domain.user.UserDTO;
import com.userslivecoding.usersManagement.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        UserProvider userProvider = new UserProvider(userRepository);
        return userProvider.exec();
    }

    @GetMapping("{userName}")
    public UserDTO getOneUser(@PathVariable String userName){
        UserFinder userFinder = new UserFinder(userRepository);
        return  userFinder.exec(userName);
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO userToCreate) {
        UserCreator userCreator = new UserCreator(userRepository);
        return userCreator.exec(userToCreate);
    }
}
