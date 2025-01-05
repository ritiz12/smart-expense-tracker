package com.example.Expenses_Tracker.expenses.data;

import java.time.Month;

public class GenerateMonthlyExpensesRequest{
    String monthName ;
    public GenerateMonthlyExpensesRequest(final String monthName) {
        this.monthName = monthName;
    }
    public String getMonthName() {
        return monthName;
    }
}
