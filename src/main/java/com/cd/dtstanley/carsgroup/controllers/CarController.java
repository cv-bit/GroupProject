package com.cd.dtstanley.carsgroup.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cd.dtstanley.carsgroup.Services.CarService;
import com.cd.dtstanley.carsgroup.Services.RatingService;
import com.cd.dtstanley.carsgroup.Services.TitleService;
import com.cd.dtstanley.carsgroup.Services.UserService;
import com.cd.dtstanley.carsgroup.models.Car;
import com.cd.dtstanley.carsgroup.models.Rating;
import com.cd.dtstanley.carsgroup.models.Title;
import com.cd.dtstanley.carsgroup.models.User;


@Controller
@RequestMapping("/cars")
public class CarController {
	@Autowired
	private CarService cService;
	@Autowired
	private TitleService tService;
	@Autowired
	private UserService uService;
	@Autowired
	private RatingService rService;

	@GetMapping("")
	public String index(Model viewModel, HttpSession session, @ModelAttribute("rating") Rating rating) {
		if(session.getAttribute("user_id").equals(null)) {
			return "redirect:/";
		}
		User user = this.uService.getSingleUser((Long)session.getAttribute("user_id"));
		List<Car> allCars= this.cService.getAllCars();
		viewModel.addAttribute("allCars", allCars);
		viewModel.addAttribute("user", user);
		return "cars/index.jsp";
	}
	
	@GetMapping("/add")
	public String addCar(@ModelAttribute("car") Car car) {
		return "cars/add.jsp";
	}
	
	@PostMapping("/add")
	public String addC(@Valid @ModelAttribute("car") Car car, BindingResult result, HttpSession session) {
		if(result.hasErrors()) {
			return "cars/add.jsp";
		}
		Long userId = (Long)session.getAttribute("user_id"); //instead of having a hidden form, this and next 2 lines
		User owner = this.uService.getSingleUser(userId);
		car.setOwner(owner); //setting the user as the owner of this car
		this.cService.createCar(car);
		return "redirect:/cars";
		//		return "cars/index.jsp";
	}
	
	@PostMapping("/addOldWay")
	public String addCarToDB(@RequestParam("make") String make, @RequestParam("model") String model, @RequestParam("color") String color, @RequestParam("year") int year, @RequestParam("transmission") String transmission, RedirectAttributes redirectAttr ) {
		ArrayList<String> errors = new ArrayList<String>();
		if(make.equals("")) {
			errors.add("Hey there, you forgot to add a make!");
		}
		if(errors.size() > 0) {
			for(String e : errors) {
				redirectAttr.addFlashAttribute("errors", e);
			}
			return "redirect:/add";
		}
		this.cService.createCarOldway(make, model, color, year, transmission);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String updateCar(@PathVariable("id") Long id, Model viewModel, @ModelAttribute("title") Title title, @ModelAttribute("car") Car car, HttpSession session) {
		viewModel.addAttribute("car", cService.getSingleCar(id));
		viewModel.addAttribute("userId", (Long)session.getAttribute("user_id"));
		return "cars/show.jsp";
	}
	
	@PostMapping("/addTitle")
	public String addTitle(@Valid @ModelAttribute("title") Title title, BindingResult result, Model viewModel) {
		Long carId = title.getCar().getId();
		if(result.hasErrors()) {
			viewModel.addAttribute("car", cService.getSingleCar(carId));
			return "cars/show.jsp";
		} else {
			this.tService.create(title);

			return "redirect:/cars/" + carId;
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCar(@PathVariable("id") Long id) {
		this.cService.deleteCar(id);
		return "redirect:/";
	}
	
	// Process Editing Car Details
	@PostMapping("/edit/{id}")
	public String editCar(@Valid @ModelAttribute("car") Car car, BindingResult result, @PathVariable("id") Long id, Model viewModel, @ModelAttribute("title") Title title) {
		Long carId = car.getId();
		if(result.hasErrors()) {			
			return "cars/show.jsp";
		}
		this.cService.updateCar(car);
		return "redirect:/cars/" + carId;
	}
	
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");	
		Long carId = id;
		User liker = this.uService.getSingleUser(userId);
		Car likedCar = this.cService.getSingleCar(carId);
		this.cService.addLiker(liker, likedCar);
		return "redirect:/cars";
	}
	
	@GetMapping("/unlike/{id}")
	public String unlike(@PathVariable("id") Long id, HttpSession session) {
		Long userId = (Long)session.getAttribute("user_id");	
		Long carId = id;
		User liker = this.uService.getSingleUser(userId);
		Car likedCar = this.cService.getSingleCar(carId);
		this.cService.removeLiker(liker, likedCar);
		return "redirect:/cars";
	}
	
	@GetMapping("user/{id}") 
	public String profile(@PathVariable("id")Long id, Model viewModel) {
		viewModel.addAttribute("user", this.uService.getSingleUser(id));
		return "profile.jsp";
	}
	
	@PostMapping("/rate/{id}") 
	public String rateCar(@RequestParam("rating") int rating, @PathVariable("id") Long id, Model viewModel, HttpSession session) {
		//grab car by Id
		Car car= this.cService.getSingleCar(id);
		Long user_id = (Long)session.getAttribute("user_id");
		User user = this.uService.getSingleUser(user_id);
		Rating newRating = new Rating();
		
		newRating.setUser(user);
		newRating.setCar(car);
		newRating.setRating(rating);
		this.rService.createRating(newRating);
		return "redirect:/cars/{id}";
	}

}

