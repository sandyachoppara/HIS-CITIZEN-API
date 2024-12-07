package com.his.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.his.dto.CitizenDTO;
import com.his.dto.LoginDTO;
import com.his.dto.ResetPwdDTO;
import com.his.service.CitizenService;

import jakarta.mail.MessagingException;

@RestController
@RefreshScope
public class CitizenRestController {
	
	@Autowired
	CitizenService citizenService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCitizen(@RequestBody CitizenDTO citizenDto) throws MessagingException{
		boolean status= citizenService.registerCitizen(citizenDto);
		if(status)
		return new ResponseEntity<>("Citizen Registered Succesfully", HttpStatus.CREATED);
		return new ResponseEntity<>("Registration not Successfull!{Email already registered/ Error occured", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginCitizen(@RequestBody LoginDTO loginDto){
		CitizenDTO loginCitizen = citizenService.loginCitizen(loginDto);
		if(loginCitizen!=null)
		{
			if(loginCitizen.getIsPwdUpdated().equals("N"))
				return new ResponseEntity<>("Please reset your password", HttpStatus.OK); 
				//redirect to reset page
			else
		      return new ResponseEntity<>("Login Successfull", HttpStatus.OK);
				// redirect to dashboard page
		}
		return new ResponseEntity<>("Invalid Logn UserName/Password !!", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/reset-pwd")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPwdDTO resetPwdDto){
		boolean status = citizenService.resetPassword(resetPwdDto);
		if(status)
		return new ResponseEntity<>("Password Changed Successfully", HttpStatus.OK);
		return new ResponseEntity<>("Error Occured, Password reset is not successful !!", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/citizen-dashboard/{id}")
	public ResponseEntity<String> citizenDashboard(@PathVariable("id") Long id){
		return new ResponseEntity<>("Citizen DashBoard Page of " + id, HttpStatus.OK);
	}
	
	@GetMapping("/citizen/{id}")
	public ResponseEntity<CitizenDTO> getCitizen(@PathVariable("id") Long id){
		CitizenDTO citizen = citizenService.getCitizen(id);
		return new ResponseEntity<>(citizen, HttpStatus.OK);
	}

}
