package com.example.Expenses_Tracker.billManagement.data;

import java.util.ArrayList;
import java.util.Collection;

public class GetBillResponse {
    Collection<BillSummary> billSummaries;

    public void addBillSummary(final BillSummary summary) {
        if(billSummaries == null)
        {
            billSummaries = new ArrayList<>();
        }
        billSummaries.add(summary);
    }
    public Collection<BillSummary> getBillSummaries() {
        return billSummaries;
    }



}
