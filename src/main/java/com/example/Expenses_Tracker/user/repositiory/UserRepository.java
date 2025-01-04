package com.example.Expenses_Tracker.user.repositiory;

import com.example.Expenses_Tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
