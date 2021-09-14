package com.tfg.saving.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tfg.saving.backend.model.Deposit;
import com.tfg.saving.backend.model.dao.IDepositDao;
import com.tfg.saving.backend.response.DepositResponse;

@Service
public class DepositService implements IDepositService{
	
	private static final Logger log = LoggerFactory.getLogger(DepositService.class);
	
	@Autowired
	private IDepositDao savingDao;
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<DepositResponse> consultSaving() {
		
		log.info("método consultSaving()");
		
		DepositResponse response = new DepositResponse();
		
		
		try {
			List<Deposit> saving = (List<Deposit>) savingDao.findAll();
			response.setSaving(saving);
			
			
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<DepositResponse> consultSavingId(Long id) {
		
		log.info("método consultSavingId(Long id)");
		
		DepositResponse response = new DepositResponse();
		
		try {
			
			Optional<Deposit> saving = savingDao.findById(id);
			
			if(saving.isPresent()) {
				List<Deposit> savingFound = new ArrayList<Deposit>();
				savingFound.add(saving.get());
				response.setSaving(savingFound);
			}else {
				log.error("Ingreso no encontrado");
				return new ResponseEntity<DepositResponse>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<DepositResponse> saveSaving(Deposit saving) {
		
		log.info("método saveSaving(Saving saving)");
		
		DepositResponse response = new DepositResponse();
		
		try {
			if(saving != null) {
				savingDao.save(saving);
				List<Deposit> savings = new ArrayList<Deposit>();
				savings.add(saving);
				response.setSaving(savings);
			}else {
				log.error("Ingreso no guardado");
				return new ResponseEntity<DepositResponse>(response, HttpStatus.BAD_REQUEST);
			}
		
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}
	
	@Override
	@Transactional
	public ResponseEntity<DepositResponse> modifySaving(Long id, Deposit saving) {
		
		log.info("método modifySaving(Long id, Saving saving)");
		
		DepositResponse response = new DepositResponse();
		
		try {
			
			Optional<Deposit> savingFound = savingDao.findById(id);
			
			if(savingFound.isPresent()) {
				
				savingFound.get().setConcept(saving.getConcept());
				savingFound.get().setSaving(saving.getSaving());
				savingFound.get().setDate(saving.getDate());
				savingFound.get().setComment(saving.getComment());
				
				savingDao.save(savingFound.get());
				
				List<Deposit> savingModified = new ArrayList<Deposit>();
				savingModified.add(savingFound.get());
				response.setSaving(savingModified);
				
			}else {
				log.error("Ingreso no modificado");
				return new ResponseEntity<DepositResponse>(response, HttpStatus.BAD_REQUEST);
			}
		
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DepositResponse> deleteSaving(Long id) {
		log.info("método deleteSaving(Long id)");
		
		DepositResponse response = new DepositResponse();
		
		try {
			
			Optional<Deposit> saving = savingDao.findById(id);
			
			if(saving.isPresent()) {
				List<Deposit> savingDeleted = new ArrayList<Deposit>();
				savingDeleted.add(saving.get());
				response.setSaving(savingDeleted);
				savingDao.deleteById(id);
			}else {
				log.error("Ingreso no eliminado");
				return new ResponseEntity<DepositResponse>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<DepositResponse> consultSavingConcept(String concept) {
		
		log.info("método consultSavingConcept(String concept)");
		
		DepositResponse response = new DepositResponse();
		List<Deposit> savingFound = new ArrayList<Deposit>();
		
		try {
			
			if(concept != null) {
				List<Deposit> savings = (List<Deposit>) savingDao.findAll();
				for(int i=0; i < savings.size(); i++) {
					if(savings.get(i).getConcept().equalsIgnoreCase(concept)) {
						savingFound.add(savings.get(i));
					}
				}
				
				response.setSaving(savingFound);
		
			}else {
				log.error("Concepto no seleccionado");
				return new ResponseEntity<DepositResponse>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<DepositResponse> consultSavingDay(LocalDate date) {
		log.info("método consultSavingDay(Date date)");
		
		DepositResponse response = new DepositResponse();
		List<Deposit> savingFound = new ArrayList<Deposit>();
		
		try {
			if(date != null) {
				List<Deposit> savings = (List<Deposit>) savingDao.findAll();
				for(int i=0; i < savings.size(); i++) {
					if(savings.get(i).getDate().compareTo(date) == 0) {
						savingFound.add(savings.get(i));
					}
				}
				
				response.setSaving(savingFound);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<DepositResponse> consultSavingMonth(LocalDate month) {
		
		log.info("método consultSavingMonth(Date date)");
	
		DepositResponse response = new DepositResponse();
		List<Deposit> savingFound = new ArrayList<Deposit>();
		
		try {
			if(month != null) {
				List<Deposit> savings = (List<Deposit>) savingDao.findAll();
				for(int i=0; i < savings.size(); i++) {
					if((month.getMonthValue() == savings.get(i).getDate().getMonthValue())
							&&
							(month.getYear() == savings.get(i).getDate().getYear())	) {
						savingFound.add(savings.get(i));
					}
				}
				
				response.setSaving(savingFound);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<DepositResponse> consultSavingYear(int year) {
		log.info("método consultSavingYear(int year)");
		
		DepositResponse response = new DepositResponse();
		List<Deposit> savingFound = new ArrayList<Deposit>();
		
		try {
			if(year != 0) {
				List<Deposit> savings = (List<Deposit>) savingDao.findAll();
				for(int i=0; i < savings.size(); i++) {
					if(savings.get(i).getDate().getYear() == year	) {
						savingFound.add(savings.get(i));
					}
				}
				
				response.setSaving(savingFound);
			}
		} catch (Exception e) {
			log.error("Error en el servidor");
			return new ResponseEntity<DepositResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<DepositResponse>(response, HttpStatus.OK);
	}

	

}
