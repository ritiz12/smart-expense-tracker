package com.example.Expenses_Tracker.expenses.data;

import java.util.ArrayList;
import java.util.Collection;

public class UpdateExpensesResponse {

    Collection<UpdateExpensesSummary> expenses;

    public void addExpenses(UpdateExpensesSummary data)
    {
        if(expenses == null)
        {
            expenses = new ArrayList<>();
        }

        expenses.add(data);
    }

    public Collection<UpdateExpensesSummary> getExpenses() {
        return expenses;
    }
}
