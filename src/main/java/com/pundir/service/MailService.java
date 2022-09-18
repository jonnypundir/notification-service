package com.pundir.service;

import com.pundir.dto.EmailDTO;
import com.pundir.dto.MailResponse;
import freemarker.template.TemplateNotFoundException;

import java.util.Map;

public interface MailService {
    MailResponse sendEmail(EmailDTO emailDTO) ;
}
