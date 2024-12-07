package com.his.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "HIS_CITIZEN")
public class Citizen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cititzen_id")
	private Long citizenId;
	private String name;
	private Long phoneNo;
	private String email;
	private String password;
	private String isPwdUpdated;
	private String gender;
	private LocalDate dob;
	private Long ssn;
	private String role;
	private LocalDate created_date;
	private LocalDate updated_date;

	public Long getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(Long citizenId) {
		this.citizenId = citizenId;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getSsn() {
		return ssn;
	}

	public void setSsn(Long ssn) {
		this.ssn = ssn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}

	public LocalDate getUpdated_date() {
		return updated_date;
	}

	public void setUpdated_date(LocalDate updated_date) {
		this.updated_date = updated_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsPwdUpdated() {
		return isPwdUpdated;
	}

	public void setIsPwdUpdated(String isPwdUpdated) {
		this.isPwdUpdated = isPwdUpdated;
	}



}
