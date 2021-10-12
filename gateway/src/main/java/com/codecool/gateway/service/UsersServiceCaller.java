package com.codecool.gateway.service;

import com.codecool.gateway.model.AuthenticationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsersServiceCaller {

    private final RestTemplate restTemplate;

    @Value("${user-provider.url}")
    private String userProviderUrl;

    public AuthenticationResponseDTO getUserByEmail(String email) throws UsernameNotFoundException {
        Optional<AuthenticationResponseDTO> responseOptional = Optional.ofNullable(
                restTemplate.getForObject(String.format("%s/authentication/%s", userProviderUrl, email), AuthenticationResponseDTO.class)
        );

        return responseOptional.orElseThrow(() -> new UsernameNotFoundException("Email " + email + " not found"));
    }

    public void registerLogin() {

    }

    public void registerLogout() {

    }
}
