package com.cd.dtstanley.carsgroup.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.dtstanley.carsgroup.models.Car;
import com.cd.dtstanley.carsgroup.models.User;
import com.cd.dtstanley.carsgroup.repositories.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository cRepo;

	public List<Car> getAllCars(){
		return this.cRepo.findAll();
	}
	
	public Car getSingleCar(Long id) {
		return this.cRepo.findById(id).orElse(null);
	}

	public Car createCar(Car newCar) {
		return this.cRepo.save(newCar);
	}

	public void deleteCar(Long id) {
		this.cRepo.deleteById(id);
	}
	
	public Car updateCar(Car car) {
		return this.cRepo.save(car);
	}
	
	public Car createCarOldway(String make, String model, String color, int year, String transmission) {
		Car newCar = new Car(make, model, color, year, transmission);
		return this.cRepo.save(newCar);
	}
	
	public void addLiker(User user, Car car) {
		List<User> likers = car.getLikers();
		likers.add(user);
		this.cRepo.save(car);
	}
	
	public void removeLiker(User user, Car car) {
		List<User> likers = car.getLikers();
		likers.remove(user);
		this.cRepo.save(car);
	}
	
	
}

