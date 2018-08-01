package com.yxu.mvcangular.service;

import java.util.List;

import com.yxu.mvcangular.model.Car;

public interface CarService {

	com.yxu.mvcangular.model.Car findById(long id);

	// Car findByName(String name);

	void saveCar(Car Car);

	void updateCar(Car Car);

	void deleteCarById(long id);

	List<Car> findAllCars();

	void deleteAllCars();

	public boolean isCarExist(Car Car);

}
