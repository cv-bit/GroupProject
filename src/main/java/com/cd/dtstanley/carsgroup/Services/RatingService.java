package com.cd.dtstanley.carsgroup.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.dtstanley.carsgroup.models.Rating;
import com.cd.dtstanley.carsgroup.repositories.RatingRepository;

@Service
public class RatingService {

	
	// Dependency Injection
	@Autowired
	private RatingRepository rRepo;
	
	public void createRating(Rating rating) {
		this.rRepo.save(rating);
	}
}
