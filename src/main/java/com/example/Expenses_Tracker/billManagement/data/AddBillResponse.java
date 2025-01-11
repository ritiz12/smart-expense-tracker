package com.example.Expenses_Tracker.billManagement.data;

import java.util.ArrayList;
import java.util.Collection;

public class AddBillResponse {

    Collection<BillSummary> billData ;

    public void addBillData(BillSummary data)
    {
        if(billData == null)
        {
            billData = new ArrayList<>();
        }
        billData.add(data);
    }

    public Collection<BillSummary> getBillData() {
        return billData;
    }
}
