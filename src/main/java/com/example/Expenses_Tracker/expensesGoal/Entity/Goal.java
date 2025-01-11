package com.example.Expenses_Tracker.expensesGoal.Entity;

import com.example.Expenses_Tracker.common.data.ExpenseCategory;
import com.example.Expenses_Tracker.common.data.TimePeriod;
import com.example.Expenses_Tracker.user.entity.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Time;

@Entity
@Table(name = "Goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalId;

    @Enumerated(EnumType.STRING)

    @Column(name="category")
    private ExpenseCategory category ;

    @Enumerated(EnumType.STRING)
    @Column(name = "timePeriod")
    private TimePeriod timePeriod ;

    @Column(name = "amount")
    private BigDecimal amount;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user ;

    public Goal(){
        super();
    }

    public Goal(final Long goalId, final ExpenseCategory category, final TimePeriod timePeriod, final BigDecimal amount, final User user) {
        this.goalId = goalId;
        this.category = category;
        this.timePeriod = timePeriod;
        this.amount = amount;
        this.user = user;
    }

    public Long getGoalId() {
        return goalId;
    }
    public void setGoalId(final Long goalId) {
        this.goalId = goalId;
    }
    public ExpenseCategory getCategory() {
        return category;
    }
    public void setCategory(final ExpenseCategory category) {
        this.category = category;
    }
    public TimePeriod getTimePeriod() {
        return timePeriod;
    }
    public void setTimePeriod(final TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
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
