package com.codecool.userprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthenticationDTO {
    private String id;
    private String email;
    private String password;
    private List<String> roles;
}
