package com.example.Expenses_Tracker.expenses.data;

public class GetMonthWiseExpenseRequest {
    String monthName ;

    public GetMonthWiseExpenseRequest(final String monthName) {
        this.monthName = monthName;
    }

    public String getMonthName() {
        return monthName;
    }
}
