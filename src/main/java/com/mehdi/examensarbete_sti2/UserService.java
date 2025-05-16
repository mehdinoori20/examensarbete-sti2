package com.mehdi.examensarbete_sti2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Registrera användare
    public void registerUser(User user) {
        // Spara användaren i databasen
        userRepository.save(user);
    }

    public User findByUsernameOrEmail(String usernameOrEmail) {
        List<User> users = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);

        if (users.isEmpty()) {
            return null; // eller kasta ett undantag
        } else if (users.size() > 1) {
            throw new IllegalStateException("Flera användare hittades med samma användarnamn eller e-post: " + usernameOrEmail);
        }

        return users.get(0);
    }

}