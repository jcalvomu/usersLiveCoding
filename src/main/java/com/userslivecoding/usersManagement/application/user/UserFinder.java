package com.userslivecoding.usersManagement.application.user;

import com.userslivecoding.usersManagement.domain.user.User;
import com.userslivecoding.usersManagement.domain.user.UserDTO;
import com.userslivecoding.usersManagement.domain.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFinder {

    UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> exec(String userNameToFind) {
        List<User> userList = userRepository.findByName(userNameToFind);
        return userList.stream().map(user -> new UserDTO(user.getName(), user.getAge())).collect(Collectors.toList());
    }
}
