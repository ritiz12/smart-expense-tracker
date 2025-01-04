package com.example.Expenses_Tracker.expenses.data;

import com.example.Expenses_Tracker.common.data.ExpenseCategory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class GetCategoriesWiseTotalExpensesResponse {

    Collection<GetCategorieswiseTotalExpensesSummary> categoryWiseTotalAmount ;

    public void addCategoryWiseAmount(GetCategorieswiseTotalExpensesSummary data)
    {
        if(categoryWiseTotalAmount == null)
        {
            categoryWiseTotalAmount = new ArrayList<>();
        }
        categoryWiseTotalAmount.add(data);
    }
    public Collection<GetCategorieswiseTotalExpensesSummary> getCategoryWiseTotalAmount() {
        return categoryWiseTotalAmount;
    }
}
