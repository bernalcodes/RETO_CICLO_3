package com.app.web.controller;

import java.text.NumberFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entity.History;
import com.app.web.entity.Vehicle;
import com.app.web.service.HistoryServiceImpl;
import com.app.web.service.VehicleServiceImpl;

@Controller
public class VehicleController {
    @Autowired
	private VehicleServiceImpl service;

	@Autowired
	private HistoryServiceImpl historyService;

	// CREATE
	@GetMapping("/vehicles/register")
	public String registerVehicleForm(Model model) {
		Vehicle vehicle = new Vehicle();
		model.addAttribute("vehicle", vehicle);
		return "vehicle_registration";
	}

	@PostMapping("/vehicles")
	public String storeVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
		service.storeVehicle(vehicle);
		return "redirect:/vehicles";
	}

	// READ
	@GetMapping("/vehicles")
	public String listVehicles(Model model) {
		model.addAttribute("vehicles", service.listAllVehicles());
		return "vehicle_list";
	}

	// UPDATE
    @GetMapping("/vehicles/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", service.getVehicleById(id));
		return "vehicle_update";
    }

	@PostMapping("/vehicles/edit/save/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle vehicle, Model model) {
        Vehicle existentVehicle = service.getVehicleById(id);
        existentVehicle.setId(id);
        existentVehicle.setPlate(vehicle.getPlate());
		existentVehicle.setEntry(vehicle.getEntry());
        existentVehicle.setBrand(vehicle.getBrand());
        existentVehicle.setModel(vehicle.getModel());
        existentVehicle.setColor(vehicle.getColor());
        existentVehicle.setOwner(vehicle.getOwner());

        service.updateVehicle(existentVehicle);

		return "redirect:/vehicles";
    }

	// DELETE
	@GetMapping("/vehicles/retire/{id}")
	public String retireVehicle(@PathVariable Long id, Model model) {
		model.addAttribute("vehicle", service.getVehicleById(id));
		return "vehicle_exit";
	}

	@PostMapping("/vehicles/retire/{id}")
	public String retireVehiclePrePayment(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle vehicle,
			Model model) {
		Vehicle existentVehicle = service.getVehicleById(id);
		existentVehicle.setExit(vehicle.getExit());
		model.addAttribute("vehicle", existentVehicle);
		return "payment_exit";
	}

	@PostMapping("/vehicles/retire/payment/{id}")
	public String calculatePayment(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle vehicle, Model model) {
		Vehicle existentVehicle = service.getVehicleById(id);
		existentVehicle.setPayment(service.calculatePayment(existentVehicle.getEntry(), existentVehicle.getExit()));

		service.updateVehicle(existentVehicle);

		String paymentString = NumberFormat.getCurrencyInstance(new Locale("en", "US"))
				.format(existentVehicle.getPayment()) + " COP";

		model.addAttribute("vehicle", service.getVehicleById(id));
		model.addAttribute("paymentString", paymentString);

		History entry = new History();

		entry.setPlate(existentVehicle.getPlate());
		entry.setEntry(existentVehicle.getEntry());
		entry.setExit(existentVehicle.getExit());
		entry.setPayment(existentVehicle.getPayment());

		historyService.storeEntry(entry);

		service.deleteVehicle(id);

		return "redirect:/vehicles";
	}
}
