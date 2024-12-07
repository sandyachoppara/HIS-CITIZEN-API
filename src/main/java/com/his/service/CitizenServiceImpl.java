package com.his.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.his.dto.CitizenDTO;
import com.his.dto.LoginDTO;
import com.his.dto.ResetPwdDTO;
import com.his.entity.Citizen;
import com.his.repository.CitizenRepository;
import com.his.utils.EmailUtils;

import jakarta.mail.MessagingException;

@Service
public class CitizenServiceImpl implements CitizenService{
	
	@Autowired
	CitizenRepository citizenRepo;
	
	@Autowired
	EmailUtils emailUtils;
	
	Random random = new Random();

	@Override
	public boolean registerCitizen(CitizenDTO citizenDto) throws MessagingException {
		Citizen citizen=citizenRepo.findByEmail(citizenDto.getEmail());
		if(citizen!=null) {
			return false;
		}else {
			citizen=new Citizen();
		BeanUtils.copyProperties(citizenDto, citizen);
		String password = genertedRandomPwd();
		citizen.setIsPwdUpdated("N");
		citizen.setPassword(password);
		if (citizenRepo.save(citizen) != null) {
			String subject = "Admin-Registration successful, Please Login!";
			String body = "Your password is : " + password;
			emailUtils.sendEmail(subject, citizen.getEmail(), body);
			return  true;
		}
		}
		return false;
	}
	private String genertedRandomPwd() {
		String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz@#$%^&*()!";
		
		int index;
		StringBuffer pwd = new StringBuffer(6);
		char c;
		for (int i = 0; i < 5; i++) {
			index = random.nextInt(alphaNumeric.length() - 1);
			c = alphaNumeric.charAt(index);
			pwd.append(c);
		}
		return pwd.toString();
	}


	@Override
	public CitizenDTO loginCitizen(LoginDTO loginDto) {
		Citizen citizen = citizenRepo.findByEmailAndPassword(loginDto.getEmail(),loginDto.getPassword());
		CitizenDTO citizenDto =new CitizenDTO();
		if(citizen!=null) {
			BeanUtils.copyProperties(citizen, citizenDto); 
			return citizenDto;
		}
		return null;
	}

	@Override
	public boolean resetPassword(ResetPwdDTO resetPwdDto) {
		Citizen citizen = citizenRepo.findByEmail(resetPwdDto.getEmail());
		if(citizen!=null) {
			citizen.setPassword(resetPwdDto.getConfirmPwd());
			citizen.setIsPwdUpdated("Y");
			citizenRepo.save(citizen);
			return true;
		}
		return false;
	}
	@Override
	public CitizenDTO getCitizen(Long citizenId) {
		// TODO Auto-generated method stub
		Citizen citizen= citizenRepo.findById(citizenId).orElseThrow();
		 CitizenDTO citizenDto =new CitizenDTO();
			if(citizen!=null) {
				BeanUtils.copyProperties(citizen, citizenDto); 
				return citizenDto;
			}
			return null;
	}
}
