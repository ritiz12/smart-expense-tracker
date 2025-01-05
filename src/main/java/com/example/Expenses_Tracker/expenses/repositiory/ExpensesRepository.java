package com.example.Expenses_Tracker.expenses.repositiory;

import com.example.Expenses_Tracker.expenses.entity.Expenses;
import com.example.Expenses_Tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;

public interface ExpensesRepository extends JpaRepository<Expenses , Long> {
    List<Expenses> findByUserAndDate(User user, Date date);

    @Query("SELECT e FROM Expenses e WHERE e.user = :user AND e.date BETWEEN :startDate AND :endDate")
    List<Expenses> findByUserAndDateRange(@Param("user") User user, @Param("startDate") Date startDate, @Param("endDate") Date endDate);


    List<Expenses> findByUser(User user);
}
