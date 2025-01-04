package com.example.Expenses_Tracker.expensesGoal.repositiory;

import com.example.Expenses_Tracker.expensesGoal.Entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepositiory extends JpaRepository<Goal ,Long > {

}
