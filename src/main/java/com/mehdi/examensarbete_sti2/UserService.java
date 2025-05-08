package com.mehdi.examensarbete_sti2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Registrera användare
    public void registerUser(User user) {
        // Spara användaren i databasen
        userRepository.save(user);
    }

    // Hitta användare baserat på användarnamn eller email (kan användas för inloggning)
    public User findByUsernameOrEmail(String usernameOrEmail) {
        return userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
    }
}