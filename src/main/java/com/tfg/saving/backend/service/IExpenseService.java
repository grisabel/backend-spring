package com.tfg.saving.backend.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.tfg.saving.backend.model.Expense;
import com.tfg.saving.backend.response.ExpenseResponse;


public interface IExpenseService {

	public ResponseEntity<ExpenseResponse> consultExpense();
	public ResponseEntity<ExpenseResponse> consultExpenseId(Long id);
	public ResponseEntity<ExpenseResponse> consultExpenseConcept(String concept);
	public ResponseEntity<ExpenseResponse> consultExpenseDay(LocalDate date);
	public ResponseEntity<ExpenseResponse> consultExpenseMonth(LocalDate month);
	public ResponseEntity<ExpenseResponse> consultExpenseYear(int year);
	public ResponseEntity<ExpenseResponse> saveExpense(Expense expense);
	public ResponseEntity<ExpenseResponse> modifyExpense(Long id, Expense expense);
	public ResponseEntity<ExpenseResponse> deleteExpense(Long id);

}
