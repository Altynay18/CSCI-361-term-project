//package com.example.demo;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class DemoApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//	}
//
//}
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.example.demo.data.Guest;
import com.example.demo.data.GuestRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private JavaMailSender javaMailSender;
    GuestRepository guestRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

//    @Override
//    public void run(String... args) {
//
//        System.out.println("Sending Email...");
//
//      
//        
//            sendEmail();
//            //sendEmailWithAttachment();
//
//       
//        System.out.println("Done");
//
//    }
//
//    void sendEmail() {
//
//        SimpleMailMessage msg = new SimpleMailMessage();
//        Iterable<Guest> guests = guestRepository.findAll();
//        for(Guest guest: guests) {
//        	
//        	 msg.setTo(guest.getEmail());
//        }
//       
//
//        msg.setSubject("\"Hedgehogs\"hotel chain UPDATES");
//        msg.setText("There were updates in our hotel Chain, Visit the Website and learn new prices");
//
//        javaMailSender.send(msg);
//
//    }
//
//    void sendEmailWithAttachment() throws MessagingException, IOException {
//
//        MimeMessage msg = javaMailSender.createMimeMessage();
//
//        // true = multipart message
//        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//        helper.setTo("1@gmail.com");
//
//        helper.setSubject("Testing from Spring Boot");
//
//        // default = text/plain
//        //helper.setText("Check attachment for image!");
//
//        // true = text/html
//        helper.setText("<h1>Check attachment for image!</h1>", true);
//
//        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));
//
//        javaMailSender.send(msg);
//
//    }
}