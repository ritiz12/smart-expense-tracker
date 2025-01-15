package com.example.Expenses_Tracker.billManagement.controller;

import com.example.Expenses_Tracker.billManagement.core.BillService;
import com.example.Expenses_Tracker.billManagement.data.AddBillRequest;
import com.example.Expenses_Tracker.billManagement.data.AddBillResponse;
import com.example.Expenses_Tracker.billManagement.data.UpdateBillRequest;
import com.example.Expenses_Tracker.billManagement.data.UpdateBillResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/bill")
public class BillAPI {

    @Autowired
    BillService billService;

    @PostMapping("/add-bill")
    ResponseEntity<AddBillResponse> addBill(@RequestBody AddBillRequest addBillRequest){
        final var respnse = billService.addBill(addBillRequest);
      return  ResponseEntity.ok(respnse);
    }

   @DeleteMapping("/delete-bill/{billId}")
    ResponseEntity<String> deleteBill(@PathVariable Long billId){
        final var response = billService.deleteBill(billId);
        return ResponseEntity.ok(response);
   }

   @PutMapping("/update-bill")
    ResponseEntity<UpdateBillResponse> updateBill(@RequestBody UpdateBillRequest updateBillRequest){
        final var response = billService.updateBill(updateBillRequest);
        return ResponseEntity.ok(response);
   }



}
