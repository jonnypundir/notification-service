package com.pundir.controller;

import com.pundir.dto.EmailDTO;
import com.pundir.dto.MailResponse;
import com.pundir.service.MailService;
import freemarker.template.TemplateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/mail-service")
public class EmailController {
    @Autowired
    MailService mailService;

    @PostMapping("/email")
    public ResponseEntity<MailResponse> sendEmail(@RequestBody EmailDTO emailDTO) {
        this.mailService.sendEmail(emailDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
