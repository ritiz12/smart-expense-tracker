package com.example.Expenses_Tracker.expenses.data;

import com.example.Expenses_Tracker.common.data.ExpenseCategory;

import java.math.BigDecimal;
import java.util.Date;

public class UpdateExpensesRequest {

    private Long expensesId;
    private BigDecimal      amount;
    private ExpenseCategory category;

    private String description;

    private Date date;

    public UpdateExpensesRequest(final Long expensesId, final BigDecimal amount, final ExpenseCategory category, final String description, final Date date) {
        this.expensesId = expensesId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
    }

    public Long getExpensesId() {
        return expensesId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public ExpenseCategory getCategory() {
        return category;
    }
    public String getDescription() {
        return description;
    }
    public Date getDate() {
        return date;
    }
}
