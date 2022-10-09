package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.web.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	@Query("SELECT r FROM Role r WHERE r.name = ?1")
	public Role findByName(String name);
}
