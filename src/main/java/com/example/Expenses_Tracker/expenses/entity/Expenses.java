package com.example.Expenses_Tracker.expenses.entity;

import com.example.Expenses_Tracker.common.data.ExpenseCategory;
import com.example.Expenses_Tracker.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "Expenses")
public class Expenses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expensesId;

    private BigDecimal amount ;

    @Enumerated(EnumType.STRING)
    private ExpenseCategory category;

    private String description;

    private Date date;

    @ManyToOne
    private User user;

    public Expenses(){
        super();
    }

    public Expenses(final Long expensesId, final BigDecimal amount, final ExpenseCategory category, final String description, final Date date, final User user) {
        this.expensesId = expensesId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = date;
        this.user = user;
    }

    public Long getExpensesId() {
        return expensesId;
    }
    public void setExpensesId(final Long expensesId) {
        this.expensesId = expensesId;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }
    public ExpenseCategory getCategory() {
        return category;
    }
    public void setCategory(final ExpenseCategory category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(final String description) {
        this.description = description;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(final Date date) {
        this.date = date;
    }
    public User getUser() {
        return user;
    }
    public void setUser(final User user) {
        this.user = user;
    }
}
