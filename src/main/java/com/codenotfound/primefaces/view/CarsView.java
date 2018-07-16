package com.codenotfound.primefaces.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codenotfound.primefaces.model.Car;
import com.codenotfound.primefaces.repository.CarRepository;

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
		log.info("##### ATENPUNK #####");
		cars = carRepository.findAll();
	}

	public List<Car> getCars() {
		return cars;
	}
}
