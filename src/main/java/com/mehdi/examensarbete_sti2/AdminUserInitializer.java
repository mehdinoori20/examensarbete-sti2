package com.mehdi.examensarbete_sti2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserInitializer {

    @Bean
    CommandLineRunner createAdmin(UserRepository repo, PasswordEncoder encoder) {
        return args -> {
            if (repo.findByUsernameOrEmail("test", "test@sti.com") == null) {
                User admin = new User();
                admin.setUsername("test");
                admin.setEmail("test@sti.com");
                admin.setPassword(encoder.encode("test123"));
                repo.save(admin);
                System.out.println("✅ Adminanvändare 'test' skapad.");
            } else {
                System.out.println("ℹ️ Adminanvändare finns redan.");
            }
        };
    }
}
