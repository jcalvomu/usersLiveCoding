package com.userslivecoding.usersManagement.application.user;

import com.userslivecoding.usersManagement.domain.user.User;
import com.userslivecoding.usersManagement.domain.user.UserDTO;
import com.userslivecoding.usersManagement.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreator {

    private final UserRepository userRepository;

    public UserCreator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO exec(UserDTO userToCreate) {
        User user = new User(-1,userToCreate.getName(),userToCreate.getAge());
        User createdUser =userRepository.save(user);
        return new UserDTO(createdUser.getName(),createdUser.getAge());

    }

}
