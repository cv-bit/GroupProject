package com.cd.dtstanley.carsgroup.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.dtstanley.carsgroup.models.Accessory;
import com.cd.dtstanley.carsgroup.repositories.AccessoryRepository;

@Service
public class AccessoryService {
	@Autowired
	private AccessoryRepository aRepo;
	
	public Accessory create(Accessory accessory) {
		return this.aRepo.save(accessory);
	}
}
