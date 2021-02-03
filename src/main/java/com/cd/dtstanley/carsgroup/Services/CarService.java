package com.cd.dtstanley.carsgroup.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.dtstanley.carsgroup.models.Car;
import com.cd.dtstanley.carsgroup.models.User;
import com.cd.dtstanley.carsgroup.repositories.CarRepository;

@Service
public class CarService {
	
	// Dependency Injection
	@Autowired
	private CarRepository cRepo;
		
	// Get All Cars
	public List<Car> getAllCars(){
		return this.cRepo.findAll();
	}
	
	// Get One Car
	public Car getSingleCar(Long id) {
		return this.cRepo.findById(id).orElse(null);
	}
	
	// Create A Car
	public Car createCar(Car newCar) {
		return this.cRepo.save(newCar);
	}
	
	// Delete A Car
	public void deleteCar(Long id) {
		this.cRepo.deleteById(id);
	}
	
	// Update a Car
	public Car updateCar(Car car) {
		return this.cRepo.save(car);
	}
	
	// Create Object With Parameters
	public Car createCarOldway(String make, String model, String color, int year, String transmission) {
		Car newCar = new Car(make, model, color, year, transmission);
		return this.cRepo.save(newCar);
	}
	
	// Add User To List Of Likers
	public void addLiker(User user, Car car) {
		// Get The List From The Car Object in order not to overwrite the arraylist and lose any data
		List<User> likers = car.getLikers();
		// Add The Liker or append to arraylist
		likers.add(user);
		// Update The DB
		this.cRepo.save(car);
	}
	
	// Remove User From List of Likers
	public void removeLiker(User user, Car car) {
		// Get The List From The Car Object
		List<User> likers = car.getLikers();
		// Remove The Liker
		likers.remove(user);
		// Update The DB
		this.cRepo.save(car);
	}
	
	
}//end of CarService

