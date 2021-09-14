package com.tfg.saving.backend.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.saving.backend.model.Expense;
import com.tfg.saving.backend.model.dao.IExpenseDao;
import com.tfg.saving.backend.response.ExpenseResponse;


@Service
public class ExpenseService implements IExpenseService{
	
	private static final Logger log = LoggerFactory.getLogger(DepositService.class);
	
	@Autowired
	private IExpenseDao expenseDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ExpenseResponse> consultExpense() {
		log.info("método  consultExpense()");
		
		ExpenseResponse response = new ExpenseResponse();
		
		
		try {
			List<Expense> expense = (List<Expense>) expenseDao.findAll();
			response.setExpense(expense);
			
			
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<ExpenseResponse> consultExpenseId(Long id) {
		log.info("método consultExpenseId(Long id)");
		
		ExpenseResponse response = new ExpenseResponse();
		
		try {
			
			Optional<Expense> expense = expenseDao.findById(id);
			
			if(expense.isPresent()) {
				List<Expense> expenseFound = new ArrayList<Expense>();
				expenseFound.add(expense.get());
				response.setExpense(expenseFound);
			}else {
				log.error("Gasto no encontrado");
				return new ResponseEntity<ExpenseResponse>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ExpenseResponse> saveExpense(Expense expense) {
		log.info("método saveExpense(Expense expense)");
		
		ExpenseResponse response = new ExpenseResponse();
		
		try {
			if(expense != null) {
				expenseDao.save(expense);
				List<Expense> expenses = new ArrayList<Expense>();
				expenses.add(expense);
				response.setExpense(expenses);
			}else {
				log.error("Ingreso no guardado");
				return new ResponseEntity<ExpenseResponse>(response, HttpStatus.BAD_REQUEST);
			}
		
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ExpenseResponse> modifyExpense(Long id, Expense expense) {
		log.info("método modifyExpense(Long id, Expense expense)");
		
		ExpenseResponse response = new ExpenseResponse();
		
		try {
			
			Optional<Expense> expenseFound = expenseDao.findById(id);
			
			if(expenseFound.isPresent()) {
				
				expenseFound.get().setConcept(expense.getConcept());
				expenseFound.get().setExpense(expense.getExpense());
				expenseFound.get().setDate(expense.getDate());
				expenseFound.get().setComment(expense.getComment());
				
				expenseDao.save(expenseFound.get());
				
				List<Expense> expenseModified = new ArrayList<Expense>();
				expenseModified.add(expenseFound.get());
				response.setExpense(expenseModified);
				
			}else {
				log.error("Gasto no modificado");
				return new ResponseEntity<ExpenseResponse>(response, HttpStatus.BAD_REQUEST);
			}
		
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<ExpenseResponse> deleteExpense(Long id) {
		log.info("método deleteExpense(Long id)");
		
		ExpenseResponse response = new ExpenseResponse();
		
		try {
			
			Optional<Expense> expense = expenseDao.findById(id);
			
			if(expense.isPresent()) {
				List<Expense> expenseDeleted = new ArrayList<Expense>();
				expenseDeleted.add(expense.get());
				response.setExpense(expenseDeleted);
				expenseDao.deleteById(id);
			}else {
				log.error("Gasto no eliminado");
				return new ResponseEntity<ExpenseResponse>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ExpenseResponse> consultExpenseConcept(String concept) {
		log.info("método consultExpenseConcept(String concept)");
		
		ExpenseResponse response = new ExpenseResponse();
		List<Expense> expenseFound = new ArrayList<Expense>();
		
		try {
			
			if(concept != null) {
				List<Expense> expenses = (List<Expense>) expenseDao.findAll();
				for(int i=0; i < expenses.size(); i++) {
					if(expenses.get(i).getConcept().equalsIgnoreCase(concept)) {
						expenseFound.add(expenses.get(i));
					}
				}
				
				response.setExpense(expenseFound);
		
			}else {
				log.error("Concepto no seleccionado");
				return new ResponseEntity<ExpenseResponse>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ExpenseResponse> consultExpenseDay(LocalDate date) {
		log.info("método consultExpenseDay(Date date)");
		
		ExpenseResponse response = new ExpenseResponse();
		List<Expense> expenseFound = new ArrayList<Expense>();
		
		try {
			if(date != null) {
				List<Expense> expenses = (List<Expense>) expenseDao.findAll();
				for(int i=0; i < expenses.size(); i++) {
					if(expenses.get(i).getDate().compareTo(date) == 0) {
						expenseFound.add(expenses.get(i));
					}
				}
				
				response.setExpense(expenseFound);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ExpenseResponse> consultExpenseMonth(LocalDate month) {
		log.info("método consultExpenseMonth(Date date)");
		
		ExpenseResponse response = new ExpenseResponse();
		List<Expense> expenseFound = new ArrayList<Expense>();
		
		try {
			if(month != null) {
				List<Expense> expenses = (List<Expense>) expenseDao.findAll();
				for(int i=0; i < expenses.size(); i++) {
					if((month.getMonthValue() == expenses.get(i).getDate().getMonthValue())
							&&
							(month.getYear() == expenses.get(i).getDate().getYear())	) {
						expenseFound.add(expenses.get(i));
					}
				}
				
				response.setExpense(expenseFound);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ExpenseResponse> consultExpenseYear(int year) {
		log.info("método consultExpenseYear(int year)");
		
		ExpenseResponse response = new ExpenseResponse();
		List<Expense> expenseFound = new ArrayList<Expense>();
		
		try {
			if(year != 0) {
				List<Expense> expenses = (List<Expense>) expenseDao.findAll();
				for(int i=0; i < expenses.size(); i++) {
					if(expenses.get(i).getDate().getYear() == year	) {
						expenseFound.add(expenses.get(i));
					}
				}
				
				response.setExpense(expenseFound);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<ExpenseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<ExpenseResponse>(response, HttpStatus.OK);
	}

}
