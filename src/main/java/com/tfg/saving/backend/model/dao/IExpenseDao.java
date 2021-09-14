package com.tfg.saving.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.tfg.saving.backend.model.Expense;

public interface IExpenseDao extends CrudRepository<Expense, Long>{

}
