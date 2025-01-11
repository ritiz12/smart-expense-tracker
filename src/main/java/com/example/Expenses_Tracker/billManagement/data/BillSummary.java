package com.example.Expenses_Tracker.billManagement.data;

import java.math.BigDecimal;
import java.util.Date;

public class BillSummary {
    private String   name;
    private Date     dueDate;
    private Priority priority;

    private BigDecimal amount;

    BillSummary(){
        super();
    }
    public String getName() {
        return name;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public Priority getPriority() {
        return priority;
    }
    public BigDecimal getAmount() {
        return amount;
    }

    public BillSummary(final String name, final Date dueDate, final Priority priority, final BigDecimal amount) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.amount = amount;
    }
}
