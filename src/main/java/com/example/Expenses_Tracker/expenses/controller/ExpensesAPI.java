package com.example.Expenses_Tracker.expenses.controller;

import com.example.Expenses_Tracker.expenses.core.ExpensesService;
import com.example.Expenses_Tracker.expenses.data.*;
import com.example.Expenses_Tracker.expenses.entity.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpensesAPI {

    @Autowired
    private ExpensesService expensesService;

   @PostMapping("/add-expenses")
   public ResponseEntity<AddExpensesResponse> addExpense(@RequestBody AddExpensesRequest addExpensesRequest){
        final var response = expensesService.addExpenses(addExpensesRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get-expenses")
    public ResponseEntity<GetExpensesResponse> getExpensesList(){
       final var response = expensesService.getExpensesList();
       return ResponseEntity.ok(response);
    }

    @PutMapping("/update-expenses")
    public ResponseEntity<UpdateExpensesResponse> updateExpenses(@RequestBody UpdateExpensesRequest updateExpensesRequest){
       final var response = expensesService.updateExpenses(updateExpensesRequest);
       return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete-expenses/{expensesId}")
    public ResponseEntity<String> deleteExpenses(@PathVariable Long expensesId)
    {
        final var response = expensesService.deleteExpenses(expensesId);
        return ResponseEntity.ok(response);
    }





}
