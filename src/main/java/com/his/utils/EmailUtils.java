package com.his.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	JavaMailSender javaMailSender;
	
	public void sendEmail(String subject, String to, String body) throws MessagingException{
		
		MimeMessage mimeMessage=javaMailSender.createMimeMessage();
		MimeMessageHelper msgHelper= new MimeMessageHelper(mimeMessage);
		msgHelper.setTo(to);
		msgHelper.setSubject(subject);
		msgHelper.setText(body, true);			
		javaMailSender.send(mimeMessage);
	}
}
