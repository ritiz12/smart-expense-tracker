package com.example.Expenses_Tracker.expenses.data;

import java.util.Date;

public class GenerateExpenseRequest {
    Date date ;
    public GenerateExpenseRequest(final Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
}
