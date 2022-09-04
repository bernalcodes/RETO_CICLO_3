package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entity.Vehicle;
import com.app.web.service.VehicleService;

@Controller
public class VehicleController {
    @Autowired
    private VehicleService service;

    @GetMapping(value = { "/", "/vehicles/" })
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", service.listAllVehicles());
        return "vehicles";
    }

    @GetMapping("/vehicles/register")
    public String registerVehicleForm(Model model) {
        Vehicle vehicle = new Vehicle();
        model.addAttribute("vehicle", vehicle);
        return "register_vehicle";
    }

    @PostMapping("/vehicles/")
    public String storeVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        service.storeVehicle(vehicle);
        return "redirect:/vehicles/";
    }

    @GetMapping("/vehicles/edit/{id}")
    public String editVehicleForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", service.getVehicleById(id));
        return "edit_vehicle";
    }

    @PostMapping("/vehicles/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle vehicle, Model model) {
        Vehicle existentVehicle = service.getVehicleById(id);
        existentVehicle.setId(id);
        existentVehicle.setPlate(vehicle.getPlate());
        existentVehicle.setDate(vehicle.getDate());
        existentVehicle.setBrand(vehicle.getBrand());
        existentVehicle.setModel(vehicle.getModel());
        existentVehicle.setColor(vehicle.getColor());
        existentVehicle.setOwner(vehicle.getOwner());

        service.updateVehicle(existentVehicle);

        return "redirect:/vehicles/";
    }

    @GetMapping("/vehicles/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        service.deleteVehicle(id);
        return "redirect:/vehicles/";
    }
}
