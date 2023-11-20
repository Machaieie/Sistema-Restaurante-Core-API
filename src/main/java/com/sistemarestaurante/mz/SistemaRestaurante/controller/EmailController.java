package com.sistemarestaurante.mz.SistemaRestaurante.controller;

import com.sistemarestaurante.mz.SistemaRestaurante.DTO.EmailDTO;
import com.sistemarestaurante.mz.SistemaRestaurante.exceptions.ResourceNotFoundException;
import com.sistemarestaurante.mz.SistemaRestaurante.model.EmailDetails;
import com.sistemarestaurante.mz.SistemaRestaurante.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class EmailController {

     @Autowired
     private EmailService emailService;
 
     @PostMapping("/sending-email")
     public ResponseEntity<EmailDetails> sendingEmail(@RequestBody @Valid EmailDTO emailDTO) {
         EmailDetails emailDetails = new EmailDetails();
         BeanUtils.copyProperties(emailDTO, emailDetails);
         String message = " Email: " + emailDTO.getEmailFrom() + "\n\n Mensagem: \n" + emailDTO.getText();
         emailDetails.setText(message);
         emailService.sendEmail(emailDetails);
         return new ResponseEntity<>(emailDetails, HttpStatus.CREATED);
     }

     @GetMapping("/emails")
     public ResponseEntity<Page<EmailDetails>> getAllEmails(
             @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
         return new ResponseEntity<>(emailService.findAll(pageable), HttpStatus.OK);
     }

     @GetMapping("/emails/{id}")
     public ResponseEntity<Object> getOneEmail(@PathVariable(value = "id") long id) {
         try {
             EmailDetails emailDetails = emailService.findById(id)
                     .orElseThrow(() -> new ResourceNotFoundException("Email  with id: " + id+" not found"));

             return ResponseEntity.status(HttpStatus.OK).body(emailDetails);
         } catch (ResourceNotFoundException e) {
             return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
         }
     }

}
