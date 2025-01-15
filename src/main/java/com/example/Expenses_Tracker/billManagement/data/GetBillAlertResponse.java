package com.example.Expenses_Tracker.billManagement.data;

import java.util.List;

public class GetBillAlertResponse {

    private List<String> message ;
    private List<String> priority ;

    public List<String> getMessage() {
        return message;
    }
    public List<String> getPriority() {
        return priority;
    }

    public void setMessage(final List<String> message) {
        this.message = message;
    }
    public void setPriority(final List<String> priority) {
        this.priority = priority;
    }
    public GetBillAlertResponse(final List<String> message, final List<String> priority) {
        this.message = message;
        this.priority = priority;
    }
}
