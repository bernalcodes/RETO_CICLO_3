package com.app.web.service;

import java.util.List;

import com.app.web.entity.Vehicle;

public interface VehicleService {
    public List<Vehicle> listAllVehicles();

    public Vehicle storeVehicle(Vehicle vehicle);

    public Vehicle getVehicleById(Long id);

    public Vehicle getVehicleByPlate(String plate);

    public Vehicle updateVehicle(Vehicle vehicle);

    public void deleteVehicle(Long id);

	// TODO: implementation of business logic
	public Double calculatePayment();
}
