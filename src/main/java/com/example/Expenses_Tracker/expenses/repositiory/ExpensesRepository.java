package com.example.Expenses_Tracker.expenses.repositiory;

import com.example.Expenses_Tracker.expenses.entity.Expenses;
import com.example.Expenses_Tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses , Long> {
    List<Expenses> findByUser(User user);
}
