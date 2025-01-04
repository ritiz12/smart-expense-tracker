package com.example.Expenses_Tracker.expenses.data;

import java.util.ArrayList;
import java.util.Collection;

public class GetExpensesResponse {
    Collection<GetExpensesSummary> expenseData ;

    public void addExpenseData(GetExpensesSummary data)
    {
        if(expenseData == null)
        {
            expenseData = new ArrayList<>();
        }
        expenseData.add(data);
    }

    public Collection<GetExpensesSummary> getExpenseData() {
        return expenseData;
    }
}
