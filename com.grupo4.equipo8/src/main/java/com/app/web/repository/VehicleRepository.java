package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.web.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT * FROM Vehicle v WHERE v.plate = ?1", nativeQuery = true)
    Vehicle findByPlate(String plate);
}
