package com.example.Expenses_Tracker.expensesGoal.data;

import java.util.ArrayList;
import java.util.Collection;

public class UpdateGoalResponse {
    Collection<UpdateGoalSummary> goalSummary ;

    public void addGoalData(UpdateGoalSummary data)
    {
        if(goalSummary == null)
        {
            goalSummary = new ArrayList<>();
        }
        goalSummary.add(data);
    }
    public Collection<UpdateGoalSummary> getGoalSummary() {
        return goalSummary;
    }
}
