package com.codecool.userprovider.controller;

import com.codecool.userprovider.controller.service.UserService;
import com.codecool.userprovider.model.entity.Role;
import com.codecool.userprovider.model.entity.User;
import com.codecool.userprovider.model.UserAuthenticationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @GetMapping("/authentication/{email}")
    public UserAuthenticationDTO requestForAuthentication(@PathVariable String email) {
        User user = userService.getUser(email);

        return UserAuthenticationDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                .build();
    }
}
