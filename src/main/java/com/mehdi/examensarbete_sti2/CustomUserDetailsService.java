package com.mehdi.examensarbete_sti2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findByUsernameOrEmail(username, username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Ingen användare hittades med användarnamn eller e-post: " + username);
        } else if (users.size() > 1) {
            throw new UsernameNotFoundException("Flera användare hittades med samma användarnamn eller e-post: " + username);
        }

        return new CustomUserDetails(users.get(0));
    }
}
