package com.example.Expenses_Tracker.expenses.core;

import com.example.Expenses_Tracker.expenses.data.*;
import com.example.Expenses_Tracker.expenses.entity.Expenses;
import com.example.Expenses_Tracker.expenses.repositiory.ExpensesRepository;
import com.example.Expenses_Tracker.user.core.UserService;
import com.example.Expenses_Tracker.user.entity.User;
import com.example.Expenses_Tracker.user.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    
   public AddExpensesResponse addExpenses(final AddExpensesRequest addExpensesRequest) {
       User currentUser = userService.getCurrentUser();

       User user = userRepository.findById(currentUser.getUserId())
                                 .orElseThrow(() -> new RuntimeException("user not found"));

       Expenses expenses = new Expenses();
       expenses.setAmount(addExpensesRequest.getAmount());
       expenses.setDescription(addExpensesRequest.getDescription());
       expenses.setCategory(addExpensesRequest.getCategory());
       expenses.setDate(addExpensesRequest.getDate());
       expenses.setUser(user);
       expensesRepository.save(expenses);
       AddExpensesSummary summary = new AddExpensesSummary(expenses.getAmount() , expenses.getCategory() , expenses.getDescription() , expenses.getDate());
       var response = new AddExpensesResponse();
       response.addExpenseData(summary);
       return response ;
    }

    public String deleteExpenses(final Long expensesId) {
        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        Expenses expense = expensesRepository.findById(expensesId)
                                             .orElseThrow(() -> new  RuntimeException("Expenses not found."));

        if(!Objects.equals(expense.getUser().getUserId(), user.getUserId())){
            throw new RuntimeException("Unauthorized to delete this expense.");
        }
        expensesRepository.delete(expense);

        return "Expenses deleted successfully";
    }

    public GetExpensesResponse getExpensesList() {
       User cuurentUser = userService.getCurrentUser();

       User user = userRepository.findById(cuurentUser.getUserId())
           .orElseThrow(() -> new RuntimeException("user not found"));

        List<Expenses> userExpenses = expensesRepository.findByUser(user);
        var response = new GetExpensesResponse();
        for (Expenses expense : userExpenses) {
            // Create a summary for each expense
            GetExpensesSummary summary = new GetExpensesSummary(
                expense.getAmount(),
                expense.getCategory(),
                expense.getDescription(),
                expense.getDate()
            );
            response.addExpenseData(summary);
        }
       return response;

    }

    public UpdateExpensesResponse updateExpenses(final UpdateExpensesRequest updateExpensesRequest) {

        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        Expenses expense = expensesRepository.findById(updateExpensesRequest.getExpensesId())
            .orElseThrow(() -> new  RuntimeException("Expenses not found."));

        if(!Objects.equals(expense.getUser().getUserId(), user.getUserId())){
            throw new RuntimeException("Unauthorized to update this expense.");
        }
        if(updateExpensesRequest.getAmount() != null)
        {
             expense.setAmount(updateExpensesRequest.getAmount());
        }

        if(updateExpensesRequest.getCategory() != null){
            expense.setCategory(updateExpensesRequest.getCategory());
        }

        if(updateExpensesRequest.getDescription() != null){
            expense.setDescription(updateExpensesRequest.getDescription());
        }

        if(updateExpensesRequest.getDate() != null){
            expense.setDate(updateExpensesRequest.getDate());
        }

        expensesRepository.save(expense);

        UpdateExpensesSummary summary = new UpdateExpensesSummary(
            expense.getAmount(),
            expense.getCategory(),
            expense.getDescription(),
            expense.getDate()
        );

        var response = new UpdateExpensesResponse();
        response.addExpenses(summary);
        return response;


    }
}
