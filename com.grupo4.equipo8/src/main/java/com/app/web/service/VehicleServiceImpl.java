package com.app.web.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.web.entity.Vehicle;
import com.app.web.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository repository;

    @Override
    public List<Vehicle> listAllVehicles() {
        return repository.findAll();
    }

    @Override
    public Vehicle storeVehicle(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Vehicle getVehicleByPlate(String plate) {
        return repository.findByPlate(plate);
    }

    @Override
    public Vehicle updateVehicle(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        repository.deleteById(id);
    }

	@Override
	public Integer calculatePayment(LocalDateTime entry, LocalDateTime exit) {
		Integer minutesElapsed = (int) entry.until(exit, ChronoUnit.MINUTES);

		int minuteFee = 80;
		int dayFee = 40000;
		int monthFee = 220000;

		int daysElapsed = 0;
		int monthsElapsed = 0;
		if (minutesElapsed >= 1440) {
			while (minutesElapsed >= 1440) {
				daysElapsed++;
				minutesElapsed -= 1440;
			}

			if (daysElapsed >= 30) {
				while (daysElapsed >= 30) {
					daysElapsed -= 30;
					monthsElapsed++;
				}
			}
		}

		int payment = (monthsElapsed * monthFee) + (daysElapsed * dayFee) + (minutesElapsed * minuteFee);

		if (payment < 5000) {
			return 5000;
		}

		return payment;
	}
}
