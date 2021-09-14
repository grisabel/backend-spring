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

import com.tfg.saving.backend.service.IDepositService;
import com.tfg.saving.backend.model.Deposit;
import com.tfg.saving.backend.response.DepositResponse;

@RestController
@RequestMapping
public class DepositController {

	@Autowired
	private IDepositService savingService;
	
	@GetMapping("/savings")
	public ResponseEntity<DepositResponse> consulSaving(){
		
		return savingService.consultSaving();
	}
	
	@GetMapping("/savings/{id}")
	public ResponseEntity<DepositResponse> consulSavingId(@PathVariable Long id){
		
		return savingService.consultSavingId(id);
	}
	
	@PostMapping("/savings")
	public ResponseEntity<DepositResponse> saveSaving(@RequestBody Deposit saving){
		
		return savingService.saveSaving(saving);
	}
	
	@PutMapping("/savings/{id}")
	public ResponseEntity<DepositResponse> modifysaving(@PathVariable Long id, @RequestBody Deposit saving){
		
		return savingService.modifySaving(id, saving);
	}
	
	@DeleteMapping("/savings/{id}")
	public ResponseEntity<DepositResponse> deletesaving(@PathVariable Long id){
		
		return savingService.deleteSaving(id);
	}
	
	@GetMapping("/savingsConcept/{concept}")
	public ResponseEntity<DepositResponse> consultSavingConcept(@PathVariable String concept){
		
		return savingService.consultSavingConcept(concept);
	}
	
	@GetMapping("/savingsDay/{date}")
	public ResponseEntity<DepositResponse> consultSavingDay(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		
		return savingService.consultSavingDay(date);
	}
	
	@GetMapping("/savingsMonth/{date}")
	public ResponseEntity<DepositResponse> consultSavingMonth(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date){
		
		return savingService.consultSavingMonth(date);
	}
	
	@GetMapping("/savingsYear/{year}")
	public ResponseEntity<DepositResponse> consultSavingMonth(@PathVariable int year){
		
		return savingService.consultSavingYear(year);
	}
}
