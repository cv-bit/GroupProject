package com.cd.dtstanley.carsgroup.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cd.dtstanley.carsgroup.models.Rating;


@Repository
public interface RatingRepository extends CrudRepository<Rating, Long>{
	List<Rating> findAll();
	// SELECT * FROM rating
}
//Get these free with CrudRepository
//C - Create
//R - Read
//U - Update
//D – Delete