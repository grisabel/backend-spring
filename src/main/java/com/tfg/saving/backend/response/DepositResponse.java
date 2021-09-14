package com.tfg.saving.backend.response;

import java.util.List;

import com.tfg.saving.backend.model.Deposit;



public class DepositResponse {
	
	private List<Deposit> saving;

	public List<Deposit> getSaving() {
		return saving;
	}

	public void setSaving(List<Deposit> saving) {
		this.saving = saving;
	}
	
}
