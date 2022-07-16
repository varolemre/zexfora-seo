package com.codingbytime.zexfora.configuration.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.codingbytime.zexfora.control.exception.CustomBadRequestException;
import com.codingbytime.zexfora.control.repository.UserRepository;
import com.codingbytime.zexfora.control.service.MessageResolver;
import com.codingbytime.zexfora.entity.table.User;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final MessageResolver messageResolver;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByMail(mail);
        if (optionalUser.isEmpty()) {
            throw new CustomBadRequestException(messageResolver.getMessage("login.invalid.credentials"));
        }
        User user = optionalUser.get();
        List<SimpleGrantedAuthority> grantList = new ArrayList<>();
        user.getRoles().forEach(role -> grantList.add(new SimpleGrantedAuthority(role.getName())));
        return new org.springframework.security.core.userdetails.User(
            user.getMail(),
            String.valueOf(user.getPassword()),
            grantList
        );
    }

}
