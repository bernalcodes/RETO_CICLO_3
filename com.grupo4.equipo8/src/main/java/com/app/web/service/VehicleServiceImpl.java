package com.app.web.service;

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
	public Double calculatePayment() {
		return null;
	}
}
