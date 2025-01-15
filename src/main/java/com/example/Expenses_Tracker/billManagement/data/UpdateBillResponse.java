package com.example.Expenses_Tracker.billManagement.data;

import com.example.Expenses_Tracker.billManagement.entity.Bill;

import java.util.ArrayList;
import java.util.Collection;

public class UpdateBillResponse {
    Collection<BillSummary> billSummaries;

    public void addBillSummary(final BillSummary billSummary) {
        if(billSummaries == null)
        {
            billSummaries = new ArrayList<>();
        }

        billSummaries.add(billSummary);
    }

    public Collection<BillSummary> getBillSummaries() {
        return billSummaries;
    }
}
