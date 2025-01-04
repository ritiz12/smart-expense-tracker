package com.example.Expenses_Tracker.expenses.data;

import com.example.Expenses_Tracker.common.data.ExpenseCategory;

import java.math.BigDecimal;
import java.util.Date;

public class AddExpensesSummary {
    private BigDecimal      amount;
    private ExpenseCategory category;

    private String description;

    private Date date;

    public AddExpensesSummary(final BigDecimal amount, final ExpenseCategory category, final String description, final Date date) {
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
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
