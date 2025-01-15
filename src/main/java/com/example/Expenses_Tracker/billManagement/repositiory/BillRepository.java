package com.example.Expenses_Tracker.billManagement.repositiory;

import com.example.Expenses_Tracker.billManagement.entity.Bill;
import com.example.Expenses_Tracker.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long > {

    List<Bill> findByUser(User userId);
}
