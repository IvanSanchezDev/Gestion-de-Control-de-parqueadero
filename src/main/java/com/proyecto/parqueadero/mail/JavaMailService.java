package com.proyecto.parqueadero.mail;



import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JavaMailService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	
	
	@Value("${spring.mail.username}")
	private String email;
	
	
	
	
	public void sendListEmail() {
		MimeMessage message=javaMailSender.createMimeMessage();
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy");
		DateFormat dateFormat2 = new SimpleDateFormat("hh:mm:ss");
		Date date = new Date();
			
		
		try {
			MimeMessageHelper helper=new MimeMessageHelper(message, true);
			
			helper.setTo(email);
			helper.setSubject("Cierre de caja parqueadero San Roque");	
			helper.setText("Registro de cierre de caja a las  " +  dateFormat2.format(date) + " del " + dateFormat.format(date));
			helper.addAttachment("cierreDeCaja.pdf", new File("C:/recibo/CdCaja.pdf"));
			javaMailSender.send(message);
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
