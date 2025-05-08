package com.mehdi.examensarbete_sti2;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Definiera en metod som söker användare baserat på användarnamn eller email
    User findByUsernameOrEmail(String username, String email);
}