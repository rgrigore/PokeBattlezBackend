package com.codecool.gateway.config;

import com.codecool.gateway.model.AuthenticationResponseDTO;
import com.codecool.gateway.service.UsersServiceCaller;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersServiceCaller usersServiceCaller;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        AuthenticationResponseDTO user = usersServiceCaller.getUserByEmail(s);

        return new User(
                user.getEmail(),
                user.getPassword(),
                generateAuthorities(user.getRoles())
        );
    }

    private static Collection<? extends GrantedAuthority> generateAuthorities(List<String> roles) {
        return AuthorityUtils.createAuthorityList(roles.toArray(new String[0]));
    }
}
