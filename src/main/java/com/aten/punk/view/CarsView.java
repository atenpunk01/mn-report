package com.aten.punk.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aten.punk.entity.Car;
import com.aten.punk.repository.CarRepository;

@Named
@ViewScoped
public class CarsView implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CarsView.class);

	@Inject
	private CarRepository carRepository;

	private List<Car> cars;

	@PostConstruct
	public void init() {
		log.info("##### CarsView #####");
		cars = carRepository.findAll();
	}

	public List<Car> getCars() {
		return cars;
	}
}
