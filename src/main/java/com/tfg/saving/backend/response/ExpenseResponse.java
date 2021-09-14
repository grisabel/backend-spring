package com.tfg.saving.backend.response;

import java.util.List;

import com.tfg.saving.backend.model.Expense;

public class ExpenseResponse {

	List<Expense> expense;

	public List<Expense> getExpense() {
		return expense;
	}

	public void setExpense(List<Expense> expense) {
		this.expense = expense;
	}
	
	
}
