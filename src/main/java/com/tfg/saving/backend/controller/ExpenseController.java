package com.tfg.saving.backend.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tfg.saving.backend.model.Expense;
import com.tfg.saving.backend.response.ExpenseResponse;
import com.tfg.saving.backend.service.IExpenseService;


@RestController
@RequestMapping
public class ExpenseController {
	
	@Autowired
	private IExpenseService expenseService;
	
	@GetMapping("/expenses")
	public ResponseEntity<ExpenseResponse> consulexpense(){
		
		return expenseService.consultExpense();
	}
	
	@GetMapping("/expenses/{id}")
	public ResponseEntity<ExpenseResponse> consulexpenseId(@PathVariable Long id){
		
		return expenseService.consultExpenseId(id);
	}
	
	@PostMapping("/expenses")
	public ResponseEntity<ExpenseResponse> saveExpense(@RequestBody Expense expense){
		
		return expenseService.saveExpense(expense);
	}
	
	@PutMapping("/expenses/{id}")
	public ResponseEntity<ExpenseResponse> modifyExpense(@PathVariable Long id, @RequestBody Expense expense){
		
		return expenseService.modifyExpense(id, expense);
	}
	
	@DeleteMapping("/expenses/{id}")
	public ResponseEntity<ExpenseResponse> deleteExpense(@PathVariable Long id){
		
		return expenseService.deleteExpense(id);
	}
	
	@GetMapping("/expensesConcept/{concept}")
	public ResponseEntity<ExpenseResponse> consultExpenseConcept(@PathVariable String concept){
		
		return expenseService.consultExpenseConcept(concept);
	}
	
	@GetMapping("/espensesDay/{date}")
	public ResponseEntity<ExpenseResponse> consultExpenseDay(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		
		return expenseService.consultExpenseDay(date);
	}
	
	@GetMapping("/expensesMonth/{date}")
	public ResponseEntity<ExpenseResponse> consultExpenseMonth(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		
		return expenseService.consultExpenseMonth(date);
	}
	
	@GetMapping("/expensesYear/{year}")
	public ResponseEntity<ExpenseResponse> consultExpensegMonth(@PathVariable int year){
		
		return expenseService.consultExpenseYear(year);
	}
}
