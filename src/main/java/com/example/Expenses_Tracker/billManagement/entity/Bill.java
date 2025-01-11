package com.example.Expenses_Tracker.billManagement.entity;

import com.example.Expenses_Tracker.billManagement.data.Priority;
import com.example.Expenses_Tracker.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="Bill")
public class Bill {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billId;

    @Column(name = "name")
    private String name ;

    @Column(name = "dueDate")
    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

    @Column(name = "amount")
    private BigDecimal amount;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Bill()
    {
        super();
    }
    public Bill(final String name, final Date dueDate, final Priority priority, final BigDecimal amount, final User user) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.amount = amount;
        this.user = user;
    }

    public Long getBillId() {
        return billId;
    }
    public void setBillId(final Long billId) {
        this.billId = billId;
    }
    public String getName() {
        return name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public Date getDueDate() {
        return dueDate;
    }
    public void setDueDate(final Date dueDate) {
        this.dueDate = dueDate;
    }
    public Priority getPriority() {
        return priority;
    }
    public void setPriority(final Priority priority) {
        this.priority = priority;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    public User getUser() {
        return user;
    }
    public void setUser(final User user) {
        this.user = user;
    }
}
