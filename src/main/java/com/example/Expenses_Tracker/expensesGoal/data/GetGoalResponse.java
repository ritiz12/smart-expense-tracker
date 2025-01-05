package com.example.Expenses_Tracker.expensesGoal.data;

import java.util.ArrayList;
import java.util.Collection;

public class GetGoalResponse {
    Collection<GetGoalSummary> goalSummary ;

    public void addGoalData(GetGoalSummary data)
    {
        if(goalSummary == null)
        {
            goalSummary = new ArrayList<>();
        }
        goalSummary.add(data);
    }

    public Collection<GetGoalSummary> getGoalSummary() {
        return goalSummary;
    }
}
