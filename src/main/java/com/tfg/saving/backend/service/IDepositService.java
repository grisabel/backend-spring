package com.tfg.saving.backend.service;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;

import com.tfg.saving.backend.model.Deposit;
import com.tfg.saving.backend.response.DepositResponse;


public interface IDepositService {

	public ResponseEntity<DepositResponse> consultSaving();
	public ResponseEntity<DepositResponse> consultSavingId(Long id);
	public ResponseEntity<DepositResponse> consultSavingConcept(String concept);
	public ResponseEntity<DepositResponse> consultSavingDay(LocalDate date);
	public ResponseEntity<DepositResponse> consultSavingMonth(LocalDate month);
	public ResponseEntity<DepositResponse> consultSavingYear(int year);
	public ResponseEntity<DepositResponse> saveSaving(Deposit saving);
	public ResponseEntity<DepositResponse> modifySaving(Long id, Deposit saving);
	public ResponseEntity<DepositResponse> deleteSaving(Long id);
}
