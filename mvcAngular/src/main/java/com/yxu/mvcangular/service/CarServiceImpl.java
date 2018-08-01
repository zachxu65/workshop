package com.yxu.mvcangular.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.yxu.mvcangular.model.Car;

@Service("carService")
public class CarServiceImpl implements CarService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<Car> cars;

	static {
		cars = populateDummyCars();
	}

	public List<com.yxu.mvcangular.model.Car> findAllCars() {
		return cars;
	}

	public Car findById(long id) {
		for (Car car : cars) {
			if (car.getId() == id) {
				return car;
			}
		}
		return null;
	}

	public Car findByName(String name) {
		for (Car car : cars) {
			if (car.getName().equalsIgnoreCase(name)) {
				return car;
			}
		}
		return null;
	}

	public void saveCar(Car car) {
		car.setId(counter.incrementAndGet());
		cars.add(car);
	}

	public void updateCar(Car car) {
		int index = cars.indexOf(car);
		cars.set(index, car);
	}

	public void deleteCarById(long id) {

		for (Iterator<Car> iterator = cars.iterator(); iterator.hasNext();) {
			Car car = iterator.next();
			if (car.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isCarExist(Car car) {
		return findByName(car.getName()) != null;
	}

	public void deleteAllCars() {
		cars.clear();
	}

	private static List<Car> populateDummyCars() {
		List<Car> cars = new ArrayList<Car>();
		Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti", "AMC Gremlin", "Triumph Stag", "Ford Pinto",
				"Yugo GV", "Highlander").forEach(name -> {
					Car car = new Car();
					car.setName(name);
					cars.add(car);
				});
		return cars;
	}

}
