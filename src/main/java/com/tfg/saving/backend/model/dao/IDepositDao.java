package com.tfg.saving.backend.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.tfg.saving.backend.model.Deposit;

public interface IDepositDao extends CrudRepository<Deposit, Long>{

}
