package com.example.Expenses_Tracker.expenses.data;

import com.example.Expenses_Tracker.common.data.ExpenseCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class GetCategorieswiseTotalExpensesSummary {
    private ExpenseCategory category ;

    private BigDecimal totalAmount ;
    public GetCategorieswiseTotalExpensesSummary(final ExpenseCategory category, final BigDecimal totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }
    public ExpenseCategory getCategory() {
        return category;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}
