package com.example.Expenses_Tracker.billManagement.repositiory;

import com.example.Expenses_Tracker.billManagement.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long > {
}
