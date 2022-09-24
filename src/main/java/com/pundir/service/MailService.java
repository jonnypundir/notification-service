package com.pundir.service;

import com.pundir.dto.EmailDTO;

public interface MailService {
    String sendEmail(EmailDTO emailDTO) ;
}
