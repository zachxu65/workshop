package com.yxu.mvcangular.configuration;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { HelloWorldConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		Filter[] singleton = { new CORSFilter() };
		return singleton;
	}

	// @Override
	// public void onStartup(ServletContext container) {
	//
	// // Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti", "AMC
	// // Gremlin", "Triumph Stag", "Ford Pinto",
	// // "Yugo GV", "Highlander").forEach(name -> {
	// // Car car = new Car();
	// // car.setName(name);
	// // carRepository.save(car);
	// // });
	// }

}