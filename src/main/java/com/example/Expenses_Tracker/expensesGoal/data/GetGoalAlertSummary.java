package com.example.Expenses_Tracker.expensesGoal.data;

public class GetGoalAlertSummary {
    private final String message ;

    private final String suggestion;
    public GetGoalAlertSummary(final String message, final String suggestion) {
        this.message = message;
        this.suggestion = suggestion;
    }

    public String getMessage() {
        return message;
    }
    public String getSuggestion() {
        return suggestion;
    }
}
