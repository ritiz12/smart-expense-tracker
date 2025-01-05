package com.example.Expenses_Tracker.expensesGoal.data;

import com.example.Expenses_Tracker.common.data.ExpenseCategory;
import com.example.Expenses_Tracker.common.data.TimePeriod;

import java.math.BigDecimal;

public class GetGoalSummary {
    private Long goalId;
    private ExpenseCategory category ;

    private TimePeriod timePeriod ;

    private BigDecimal amount;
    public GetGoalSummary(final Long goalId, final ExpenseCategory category, final TimePeriod timePeriod, final BigDecimal amount) {
        this.goalId = goalId;
        this.category = category;
        this.timePeriod = timePeriod;
        this.amount = amount;
    }

    public Long getGoalId() {
        return goalId;
    }
    public ExpenseCategory getCategory() {
        return category;
    }
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }
    public BigDecimal getAmount() {
        return amount;
    }
}
