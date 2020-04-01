package com.userslivecoding.usersManagement.application.user;

import com.userslivecoding.usersManagement.domain.user.User;
import com.userslivecoding.usersManagement.domain.user.UserDTO;
import com.userslivecoding.usersManagement.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserFinder {

    UserRepository userRepository;

    public UserFinder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO exec(String userNameToFind) {
        User user = userRepository.findByName(userNameToFind).orElse(null);
        if (ObjectUtils.isEmpty(user)) return null;
        return new UserDTO(user.getName(), user.getAge());
    }
}
