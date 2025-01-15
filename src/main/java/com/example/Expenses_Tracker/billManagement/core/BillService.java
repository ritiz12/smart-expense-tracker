package com.example.Expenses_Tracker.billManagement.core;

import com.example.Expenses_Tracker.billManagement.data.*;
import com.example.Expenses_Tracker.billManagement.entity.Bill;
import com.example.Expenses_Tracker.billManagement.repositiory.BillRepository;
import com.example.Expenses_Tracker.user.core.UserService;
import com.example.Expenses_Tracker.user.entity.User;
import com.example.Expenses_Tracker.user.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BillService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserService userService;

    public AddBillResponse addBill(final AddBillRequest addBillRequest) {
        User currentUser = userService.getCurrentUser();

        User user = userRepository.findById(currentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        Bill bill = new Bill();
        bill.setAmount(addBillRequest.getAmount());
        bill.setName(addBillRequest.getName());
        bill.setDueDate(addBillRequest.getDueDate());
        bill.setPriority(addBillRequest.getPriority());
        bill.setUser(user);
        Bill savedBill = billRepository.save(bill);

        AddBillResponse response = new AddBillResponse();
        BillSummary billSummary = new BillSummary(savedBill.getName(), savedBill.getDueDate(), savedBill.getPriority(), savedBill.getAmount());
        response.addBillData(billSummary);

        return response;
    }
    public String deleteBill(final Long billId) {
        User currentUser = userService.getCurrentUser();

        User user = userRepository.findById(currentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found."));

        Bill bill = billRepository.findById(billId)
                                  .orElseThrow(() -> new RuntimeException("Bill do not found."));

        if (!Objects.equals(bill.getUser().getUserId(), user.getUserId())) {
            throw new RuntimeException("Unauthorized to delete this bill.");
        }
        billRepository.delete(bill);
        return "Bill information delete successfully.";
    }
    public UpdateBillResponse updateBill(final UpdateBillRequest updateBillRequest) {
        User currentUser = userService.getCurrentUser();

        User user = userRepository.findById(currentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found."));

        Bill bill = billRepository.findById(updateBillRequest.getBillId())
                                  .orElseThrow(() -> new RuntimeException("Bill do not found."));

        if (!Objects.equals(bill.getUser().getUserId(), user.getUserId())) {
            throw new RuntimeException("Unauthorized to update this bill.");
        }

        if (updateBillRequest.getAmount() != null) {
            bill.setAmount(updateBillRequest.getAmount());
        }

        if (updateBillRequest.getName() != null) {
            bill.setName(updateBillRequest.getName());
        }

        if (updateBillRequest.getDueDate() != null) {
            bill.setDueDate(updateBillRequest.getDueDate());
        }

        if (updateBillRequest.getPriority() != null) {
            bill.setPriority(updateBillRequest.getPriority());
        }

        Bill updatedBill = billRepository.save(bill);

        final var response = new UpdateBillResponse();
        final var summary = new BillSummary(updatedBill.getName(), updatedBill.getDueDate(), updatedBill.getPriority(), updatedBill.getAmount());
        response.addBillSummary(summary);

        return response;
    }
}
