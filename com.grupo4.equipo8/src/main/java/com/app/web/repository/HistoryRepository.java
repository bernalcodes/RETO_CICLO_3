package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.web.entity.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
	@Query(value = "SELECT * FROM History h WHERE h.plate = ?1", nativeQuery = true)
	History findByPlate(String plate);
}
