package com.example.Expenses_Tracker.expensesGoal.repositiory;

import com.example.Expenses_Tracker.expensesGoal.Entity.Goal;
import com.example.Expenses_Tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepositiory extends JpaRepository<Goal ,Long > {

    List<Goal> findByUser(User user);
}
