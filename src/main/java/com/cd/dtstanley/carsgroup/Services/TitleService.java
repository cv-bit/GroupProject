package com.cd.dtstanley.carsgroup.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.dtstanley.carsgroup.models.Title;
import com.cd.dtstanley.carsgroup.repositories.TitleRepository;

@Service
public class TitleService {
	@Autowired
	private TitleRepository tRepo;

	public Title create(Title title) {
		return this.tRepo.save(title);
	}
}

