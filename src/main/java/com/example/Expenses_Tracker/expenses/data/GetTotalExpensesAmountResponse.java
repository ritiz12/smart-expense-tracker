package com.example.Expenses_Tracker.expenses.data;

import java.math.BigDecimal;

public class GetTotalExpensesAmountResponse {

    private BigDecimal totalAmount ;



    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(final BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
