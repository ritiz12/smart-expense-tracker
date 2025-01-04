package com.example.Expenses_Tracker.expenses.data;

public class DeleteExpensesRequest {
    private Long expensesId;
    public DeleteExpensesRequest(final Long expensesId) {
        this.expensesId = expensesId;
    }

    public Long getExpensesId() {
        return expensesId;
    }
}
