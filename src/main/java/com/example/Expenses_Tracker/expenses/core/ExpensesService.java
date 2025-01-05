package com.example.Expenses_Tracker.expenses.core;

import com.example.Expenses_Tracker.common.core.PdfGeneratorService;
import com.example.Expenses_Tracker.common.data.ExpenseCategory;
import com.example.Expenses_Tracker.expenses.data.*;
import com.example.Expenses_Tracker.expenses.entity.Expenses;
import com.example.Expenses_Tracker.expenses.repositiory.ExpensesRepository;
import com.example.Expenses_Tracker.user.core.UserService;
import com.example.Expenses_Tracker.user.entity.User;
import com.example.Expenses_Tracker.user.repositiory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepository expensesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PdfGeneratorService pdfGeneratorService;

    
   public AddExpensesResponse addExpenses(final AddExpensesRequest addExpensesRequest) {
       User currentUser = userService.getCurrentUser();

       User user = userRepository.findById(currentUser.getUserId())
                                 .orElseThrow(() -> new RuntimeException("user not found"));

       Expenses expenses = new Expenses();
       expenses.setAmount(addExpensesRequest.getAmount());
       expenses.setDescription(addExpensesRequest.getDescription());
       expenses.setCategory(addExpensesRequest.getCategory());
       expenses.setDate(addExpensesRequest.getDate());
       expenses.setUser(user);
       expensesRepository.save(expenses);
       AddExpensesSummary summary = new AddExpensesSummary(expenses.getAmount() , expenses.getCategory() , expenses.getDescription() , expenses.getDate());
       var response = new AddExpensesResponse();
       response.addExpenseData(summary);
       return response ;
    }
    public GetCategoriesWiseTotalExpensesResponse categoryWiseTotalAmount() {
        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        List<Expenses> userExpenses = expensesRepository.findByUser(user);

        Map<String , BigDecimal> categoryTotals = new HashMap<>();

        for(Expenses expenses : userExpenses){
            categoryTotals.put(expenses.getCategory().toString(),
                               categoryTotals.getOrDefault(expenses.getCategory().toString(), BigDecimal.valueOf(0.0)).add(expenses.getAmount()));
        }

final var response = new GetCategoriesWiseTotalExpensesResponse();

        categoryTotals.forEach((category, totalAmount) -> {
            GetCategorieswiseTotalExpensesSummary summary = new GetCategorieswiseTotalExpensesSummary(
                ExpenseCategory.valueOf(category), totalAmount);
            response.addCategoryWiseAmount(summary);
        });

        return response;

    }

    public String deleteExpenses(final Long expensesId) {
        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        Expenses expense = expensesRepository.findById(expensesId)
                                             .orElseThrow(() -> new  RuntimeException("Expenses not found."));

        if(!Objects.equals(expense.getUser().getUserId(), user.getUserId())){
            throw new RuntimeException("Unauthorized to delete this expense.");
        }
        expensesRepository.delete(expense);

        return "Expenses deleted successfully";
    }

    public ResponseEntity<byte[]> generateExpensePdf(final GenerateExpenseRequest generateExpenseRequest) {
      List<Expenses> expensesList = getExpenseListForParticularDate(generateExpenseRequest.getDate());
        byte[] pdfContent = pdfGeneratorService.generatePdfOfExpenses(expensesList);

        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expenses_receipt.pdf")
                             .contentType(MediaType.APPLICATION_PDF)
                             .body(pdfContent);
    }

    public GetExpensesResponse getDateWiseExpenses(final GetDateWiseExpenseRequest getExpensesRequest) {
         List<Expenses> expenses =  getExpenseListForParticularDate(getExpensesRequest.getDate()) ;
         final var response = new GetExpensesResponse();
         for(Expenses expense : expenses)
         {
             GetExpensesSummary summary = new GetExpensesSummary(expense.getAmount() ,expense.getCategory() , expense.getDescription(), expense.getDate());
             response.addExpenseData(summary);
         }
         return response;
    }

    public GetExpensesResponse getMonthWiseExpenses(final GetMonthWiseExpenseRequest getMonthWiseExpenseRequest) {
        YearMonth monthName = YearMonth.parse(getMonthWiseExpenseRequest.getMonthName(), DateTimeFormatter.ofPattern("yyyy-MM"));
       List<Expenses> expenses = getExpensesListForParticularMonth(monthName);
        final var response = new GetExpensesResponse();
        for(Expenses expense : expenses)
        {
            GetExpensesSummary summary = new GetExpensesSummary(expense.getAmount() ,expense.getCategory() , expense.getDescription(), expense.getDate());
            response.addExpenseData(summary);
        }
        return response;
    }

    private List<Expenses> getExpenseListForParticularDate(final Date date) {
        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));
        List<Expenses> expenses = expensesRepository.findByUserAndDate(user , date);
        return expenses;

    }

    public ResponseEntity<byte[]> generateMonthlyExpenses(final GenerateMonthlyExpensesRequest generateMonthlyExpensesRequest) {
        YearMonth monthName = YearMonth.parse(generateMonthlyExpensesRequest.getMonthName(), DateTimeFormatter.ofPattern("yyyy-MM"));
      List<Expenses>  expenseList = getExpensesListForParticularMonth(monthName);
      byte[] pdfContent = pdfGeneratorService.generatePdfOfExpenses(expenseList);

        return ResponseEntity.ok()
                             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=expenses_receipt.pdf")
                             .contentType(MediaType.APPLICATION_PDF)
                             .body(pdfContent);
    }

    private List<Expenses> getExpensesListForParticularMonth(final YearMonth monthName) {
        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));
        LocalDate startDate = monthName.atDay(1);
        LocalDate endDate = monthName.atEndOfMonth();
        List<Expenses> userExpenses = expensesRepository.findByUserAndDateRange(user,java.sql.Date.valueOf(startDate),
                                                                            java.sql.Date.valueOf(endDate));
        return userExpenses;
    }

    public GetExpensesResponse getExpensesList() {
       User cuurentUser = userService.getCurrentUser();

       User user = userRepository.findById(cuurentUser.getUserId())
           .orElseThrow(() -> new RuntimeException("user not found"));

        List<Expenses> userExpenses = expensesRepository.findByUser(user);
        var response = new GetExpensesResponse();
        for (Expenses expense : userExpenses) {
            // Create a summary for each expense
            GetExpensesSummary summary = new GetExpensesSummary(
                expense.getAmount(),
                expense.getCategory(),
                expense.getDescription(),
                expense.getDate()
            );
            response.addExpenseData(summary);
        }
       return response;

    }
    public GetTotalExpensesAmountResponse totalExpensesAmount() {
        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        List<Expenses> userExpenses = expensesRepository.findByUser(user);
        BigDecimal totalAmount = BigDecimal.ZERO;
        for(Expenses expenses : userExpenses )
        {
            totalAmount = totalAmount.add(expenses.getAmount());
        }

        GetTotalExpensesAmountResponse response = new GetTotalExpensesAmountResponse();
        response.setTotalAmount(totalAmount);

        return response ;


    }

    public UpdateExpensesResponse updateExpenses(final UpdateExpensesRequest updateExpensesRequest) {

        User cuurentUser = userService.getCurrentUser();

        User user = userRepository.findById(cuurentUser.getUserId())
                                  .orElseThrow(() -> new RuntimeException("user not found"));

        Expenses expense = expensesRepository.findById(updateExpensesRequest.getExpensesId())
            .orElseThrow(() -> new  RuntimeException("Expenses not found."));

        if(!Objects.equals(expense.getUser().getUserId(), user.getUserId())){
            throw new RuntimeException("Unauthorized to update this expense.");
        }
        if(updateExpensesRequest.getAmount() != null)
        {
             expense.setAmount(updateExpensesRequest.getAmount());
        }

        if(updateExpensesRequest.getCategory() != null){
            expense.setCategory(updateExpensesRequest.getCategory());
        }

        if(updateExpensesRequest.getDescription() != null){
            expense.setDescription(updateExpensesRequest.getDescription());
        }

        if(updateExpensesRequest.getDate() != null){
            expense.setDate(updateExpensesRequest.getDate());
        }

        expensesRepository.save(expense);

        UpdateExpensesSummary summary = new UpdateExpensesSummary(
            expense.getAmount(),
            expense.getCategory(),
            expense.getDescription(),
            expense.getDate()
        );

        var response = new UpdateExpensesResponse();
        response.addExpenses(summary);
        return response;


    }
}
