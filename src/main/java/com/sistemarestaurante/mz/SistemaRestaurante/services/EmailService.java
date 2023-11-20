package com.sistemarestaurante.mz.SistemaRestaurante.services;

import com.sistemarestaurante.mz.SistemaRestaurante.model.EmailDetails;
import com.sistemarestaurante.mz.SistemaRestaurante.model.StatusEmail;
import com.sistemarestaurante.mz.SistemaRestaurante.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailService {

     @Autowired
     EmailRepository emailRepository;
   
     @Autowired
     private JavaMailSender emailSender;

     public EmailDetails sendEmail(EmailDetails emailDetails) {
         emailDetails.setSendDateEmail(LocalDateTime.now());
         try{
             SimpleMailMessage message = new SimpleMailMessage();
             message.setFrom(emailDetails.getEmailFrom());
             message.setTo(emailDetails.getEmailTo());
             message.setSubject(emailDetails.getSubject());
             message.setText(emailDetails.getText());
             emailSender.send(message);

             emailDetails.setStatusEmail(StatusEmail.SENT);
         } catch (MailException e){
             emailDetails.setStatusEmail(StatusEmail.ERROR);
         } finally {
            return emailRepository.save(emailDetails);

         }
     }

     public Page<EmailDetails> findAll(Pageable pageable) {
         return  emailRepository.findAll(pageable);
     }

     public Optional<EmailDetails> findById(long emailId) {
         return emailRepository.findById(emailId);
     }
}
