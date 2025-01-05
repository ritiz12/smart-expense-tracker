package com.example.Expenses_Tracker.expenses.data;

import java.util.Date;

public class GetDateWiseExpenseRequest {
    Date date ;

    public GetDateWiseExpenseRequest(final Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
