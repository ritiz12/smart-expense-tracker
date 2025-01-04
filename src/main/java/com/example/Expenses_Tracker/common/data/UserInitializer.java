package com.example.Expenses_Tracker.common.data;

import com.example.Expenses_Tracker.user.entity.User;
import com.example.Expenses_Tracker.user.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(final String... args) throws Exception {
        userRepository.save(new User(null, "user1", "user1@mail.com", passwordEncoder.encode("user1")));
        userRepository.save(new User(null, "user2", "user2@mail.com", passwordEncoder.encode("user2")));
    }
}
