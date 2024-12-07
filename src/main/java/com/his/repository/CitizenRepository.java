package com.his.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.his.entity.Citizen;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
	
	Citizen findByEmailAndPassword(String email, String pwd);
	Citizen findByEmail(String email);

}
