package com.cd.dtstanley.carsgroup.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cd.dtstanley.carsgroup.models.Car;


@Repository
public interface CarRepository extends CrudRepository<Car, Long>{
	List<Car> findAll();
	// SELECT * FROM cars
}
//Get these free with CrudRepository
//C - Create
//R - Read
//U - Update
//D – Delete

