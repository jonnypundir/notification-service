package com.pundir.controller;

import com.pundir.dto.EmailDTO;
import com.pundir.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/mail-service")
public class EmailController {
    @Autowired
    private MailService mailService;

    @PostMapping("/email")
    public ResponseEntity<Map<String,String>> sendEmail(@Valid @RequestBody EmailDTO emailDTO) {
        return ResponseEntity.ok(Map.of("Mail status ",this.mailService.sendEmail(emailDTO)));
    }

}
