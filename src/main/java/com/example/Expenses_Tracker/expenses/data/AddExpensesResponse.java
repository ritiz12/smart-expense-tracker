package com.example.Expenses_Tracker.expenses.data;

import java.util.ArrayList;
import java.util.Collection;

public class AddExpensesResponse {
    Collection<AddExpensesSummary> expenseData ;

    public void addExpenseData(AddExpensesSummary data)
    {
        if(expenseData == null)
        {
            expenseData = new ArrayList<>();
        }
        expenseData.add(data);
    }

    public Collection<AddExpensesSummary> getExpenseData() {
        return expenseData;
    }
}
