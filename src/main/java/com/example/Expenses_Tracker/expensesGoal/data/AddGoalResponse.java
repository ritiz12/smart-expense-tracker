package com.example.Expenses_Tracker.expensesGoal.data;

import java.util.ArrayList;
import java.util.Collection;

public class AddGoalResponse {
    Collection<AddGoalSummary> goalSummary ;

    public void addGoalData(AddGoalSummary data)
    {
        if(goalSummary == null)
        {
            goalSummary = new ArrayList<>();
        }
        goalSummary.add(data);
    }
    public Collection<AddGoalSummary> getGoalSummary() {
        return goalSummary;
    }
}
