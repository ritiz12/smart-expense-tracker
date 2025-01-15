package com.example.Expenses_Tracker.expensesGoal.core;

import com.example.Expenses_Tracker.expenses.entity.Expenses;
import com.example.Expenses_Tracker.expenses.repositiory.ExpensesRepository;
import com.example.Expenses_Tracker.expensesGoal.Entity.Goal;
import com.example.Expenses_Tracker.expensesGoal.data.*;
import com.example.Expenses_Tracker.expensesGoal.repositiory.GoalRepositiory;
import com.example.Expenses_Tracker.user.core.UserService;
import com.example.Expenses_Tracker.user.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Expenses_Tracker.user.entity.User;

import java.math.BigDecimal;
import java.util.*;

@Service
public class GoalService {

    @Autowired
    GoalRepositiory goalRepositiory;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService ;

    @Autowired
    ExpensesRepository expensesRepository;

    public AddGoalResponse addGoal(final AddGoalRequest addGoalRequest) {

        User currentUser = userService.getCurrentUser();

        User user = userRepository.findById(currentUser.getUserId()).orElseThrow(() -> new RuntimeException("user not found."));

        Goal goal = new Goal();
        goal.setCategory(addGoalRequest.getCategory());
        goal.setTimePeriod(addGoalRequest.getTimePeriod());
        goal.setAmount(addGoalRequest.getAmount());
        goal.setUser(user);
        Goal saveGoal = goalRepositiory.save(goal);

        final var response = new AddGoalResponse();
        AddGoalSummary summary = new AddGoalSummary(saveGoal.getCategory() , saveGoal.getTimePeriod() , saveGoal.getAmount());

        response.addGoalData(summary);
        return response;

    }

    public String deleteGoal(final Long goalId) {
        User currentUser = userService.getCurrentUser();

        User user = userRepository.findById(currentUser.getUserId()).orElseThrow(() -> new RuntimeException("user not found."));

        Goal goal = goalRepositiory.findById(goalId).orElseThrow(() -> new RuntimeException("Goal Id does not found."));

        if(!Objects.equals(goal.getUser().getUserId(), user.getUserId())){
            throw new RuntimeException("Unauthorized to delete this goal.");
        }

        goalRepositiory.delete(goal);

        return "Goal delete successfully.";
    }

    public GetGoalResponse getGoal() {

        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));


        List<Goal> goals = goalRepositiory.findByUser(user);
        final var response = new GetGoalResponse();
        for(Goal goal : goals)
        {
            GetGoalSummary summary = new GetGoalSummary(goal.getGoalId(), goal.getCategory() , goal.getTimePeriod(),goal.getAmount());
            response.addGoalData(summary);
        }
        return response;
    }

    public GetGoalAlertResponse getGoalAlert() {
        User currentUser = userService.getCurrentUser();

        User user = userRepository.findById(currentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        List<Goal> goals = goalRepositiory.findByUser(user);
        List<Expenses> expenses = expensesRepository.findByUser(user);

        // Create a map to store total expenses per category in BigDecimal
        Map<String, BigDecimal> totalCategoryExpenses = new HashMap<>();

        // Calculate total expenses per category in BigDecimal
        for (Expenses expense : expenses) {
            String expenseCategory = String.valueOf(expense.getCategory());
            BigDecimal expenseAmount = expense.getAmount();

            // Sum the expenses for each category
            totalCategoryExpenses.put(expenseCategory, totalCategoryExpenses.getOrDefault(expenseCategory, BigDecimal.ZERO).add(expenseAmount));
        }
        final var response = new GetGoalAlertResponse();
      String alerts = "";

        // Check if the user has reached 80% of their goal in any category
        for (Goal goal : goals) {
            String goalCategory = String.valueOf(goal.getCategory());
            BigDecimal goalAmount = goal.getAmount();  // Assuming `amount` is the goal amount for the category

            // Get total expenses for the category
            BigDecimal totalSpent = totalCategoryExpenses.getOrDefault(goalCategory, BigDecimal.ZERO);

            // Check if the user has spent 80% or more of the goal amount in BigDecimal
            BigDecimal threshold = goalAmount.multiply(new BigDecimal("0.2"));
            if (totalSpent.compareTo(threshold) >= 0) {
                alerts = "You have spent 80% or more of your goal amount in the category '" + goalCategory + "'. Please review your spending.";
                final var summary = new GetGoalAlertSummary(alerts,"");
                response.addGoalAlert(summary);
            }
        }

        return response;
    }


    public UpdateGoalResponse updateGoal(final UpdateGoalRequest updateGoalRequest) {

        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        Goal goal = goalRepositiory.findById(updateGoalRequest.getGoalId()).orElseThrow(() -> new RuntimeException("Particular goal does not found"));

        if(!Objects.equals(goal.getUser().getUserId(), user.getUserId())){
            throw new RuntimeException("Unauthorized to update this goal.");
        }
        if(updateGoalRequest.getAmount() != null)
        {
            goal.setAmount(updateGoalRequest.getAmount());
        }

        if(updateGoalRequest.getCategory() != null)
        {
            goal.setCategory(updateGoalRequest.getCategory());
        }

        if(updateGoalRequest.getTimePeriod() != null)
        {
            goal.setTimePeriod(updateGoalRequest.getTimePeriod());
        }
        Goal updatedGoal = goalRepositiory.save(goal);
        final var response = new UpdateGoalResponse();
        UpdateGoalSummary summary = new UpdateGoalSummary(updatedGoal.getCategory() , updatedGoal.getTimePeriod() , updatedGoal.getAmount());

        response.addGoalData(summary);
        return response ;


    }
}
