package com.example.Expenses_Tracker.expensesGoal.data;

import java.util.ArrayList;
import java.util.Collection;

public class GetGoalAlertResponse {
    Collection<GetGoalAlertSummary> alerts;

    public void addGoalAlert(GetGoalAlertSummary summary)
    {
        if(alerts == null)
        {
            alerts = new ArrayList<>();
        }
        alerts.add(summary);
    }

    public Collection<GetGoalAlertSummary> getAlerts() {
        return alerts;
    }
}
