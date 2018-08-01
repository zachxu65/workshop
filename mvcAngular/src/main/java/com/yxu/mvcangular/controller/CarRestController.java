package com.yxu.mvcangular.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.yxu.mvcangular.model.Car;
import com.yxu.mvcangular.service.CarService;

@RestController
public class CarRestController {

	@Autowired
	CarService carService; // Service which will do all data retrieval/manipulation work

	// -------------------Retrieve All
	// Cars--------------------------------------------------------

	@RequestMapping(value = "/cars", method = RequestMethod.GET)
	public ResponseEntity<List<Car>> listAllCars() {
		List<Car> cars = carService.findAllCars();
		if (cars.isEmpty()) {
			return new ResponseEntity<List<Car>>(HttpStatus.NO_CONTENT);// You many decide to return
																		// HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Car>>(cars, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Car--------------------------------------------------------

	@RequestMapping(value = "/car/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Car> getCar(@PathVariable("id") long id) {
		System.out.println("Fetching Car with id " + id);
		Car car = carService.findById(id);
		if (car == null) {
			System.out.println("Car with id " + id + " not found");
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Car>(car, HttpStatus.OK);
	}

	// -------------------Create a
	// Car--------------------------------------------------------

	@RequestMapping(value = "/car/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCar(@RequestBody Car car, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Car " + car.getName());

		if (carService.isCarExist(car)) {
			System.out.println("A Car with name " + car.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		carService.saveCar(car);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/car/{id}").buildAndExpand(car.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a Car
	// --------------------------------------------------------

	@RequestMapping(value = "/car/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Car> updateCar(@PathVariable("id") long id, @RequestBody Car car) {
		System.out.println("Updating Car " + id);

		Car currentCar = carService.findById(id);

		if (currentCar == null) {
			System.out.println("Car with id " + id + " not found");
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}

		currentCar.setName(car.getName());
		// currentCar.setAddress(car.getAddress());
		// currentCar.setEmail(car.getEmail());

		carService.updateCar(currentCar);
		return new ResponseEntity<Car>(currentCar, HttpStatus.OK);
	}

	// ------------------- Delete a Car
	// --------------------------------------------------------

	@RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Car> deleteCar(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Car with id " + id);

		Car car = carService.findById(id);
		if (car == null) {
			System.out.println("Unable to delete. Car with id " + id + " not found");
			return new ResponseEntity<Car>(HttpStatus.NOT_FOUND);
		}

		carService.deleteCarById(id);
		return new ResponseEntity<Car>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Cars
	// --------------------------------------------------------

	@RequestMapping(value = "/car/", method = RequestMethod.DELETE)
	public ResponseEntity<Car> deleteAllCars() {
		System.out.println("Deleting All Cars");

		carService.deleteAllCars();
		return new ResponseEntity<Car>(HttpStatus.NO_CONTENT);
	}

}