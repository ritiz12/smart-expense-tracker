package com.example.Expenses_Tracker.expensesGoal.controller;

import com.example.Expenses_Tracker.expensesGoal.core.GoalService;
import com.example.Expenses_Tracker.expensesGoal.data.AddGoalRequest;
import com.example.Expenses_Tracker.expensesGoal.data.AddGoalResponse;
import com.example.Expenses_Tracker.expensesGoal.data.UpdateGoalRequest;
import com.example.Expenses_Tracker.expensesGoal.data.UpdateGoalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goal")
public class GoalAPI {

    @Autowired
    GoalService goalService;

    @PostMapping("/add-goal")
    ResponseEntity<AddGoalResponse> addGoal(@RequestBody AddGoalRequest addGoalRequest){
        final var response = goalService.addGoal(addGoalRequest);
            return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-goal/{goalId}")
    ResponseEntity<String> deleteGoal(@PathVariable Long goalId)
    {
        final var response = goalService.deleteGoal(goalId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update-goal")
    ResponseEntity<UpdateGoalResponse> updateGoal(@RequestBody UpdateGoalRequest updateGoalRequest)
    {
        final var response = goalService.updateGoal(updateGoalRequest);
        return ResponseEntity.ok(response);
    }



}
