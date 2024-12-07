package com.his.service;

import com.his.dto.CitizenDTO;
import com.his.dto.LoginDTO;
import com.his.dto.ResetPwdDTO;

import jakarta.mail.MessagingException;

public interface CitizenService {

	boolean registerCitizen(CitizenDTO citizenDto) throws MessagingException;

	CitizenDTO loginCitizen(LoginDTO loginDto);

	boolean resetPassword(ResetPwdDTO resetPwdDto);
	
	CitizenDTO getCitizen(Long citizenId);

}
