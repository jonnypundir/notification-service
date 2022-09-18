package com.pundir.service;

import com.pundir.dto.EmailDTO;
import com.pundir.dto.MailResponse;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class MailServiceImpl implements MailService{
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    Configuration configuration;
    @Value("${spring.mail.username}")
    private String username;

    @Override
    public MailResponse sendEmail(EmailDTO emailDTO){
        Map<String,Object> model = new HashMap<>();
        MailResponse mailResponse = new MailResponse();

        String toUser = emailDTO.getTo().replace("@gmail.com", "");
        String body = emailDTO.getBody();

        model.put("subscriber", toUser);
        model.put("signature", username);
        model.put("emailBody", body);
        model.put("location", "New Delhi, India");

        try{
            MimeMessagePreparator preparator =(MimeMessage mimeMessage)->{
                Template template = configuration.getTemplate("email-template.ftl");
                String htmlString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDTO.getTo()));
                mimeMessage.setFrom(new InternetAddress(username));
                mimeMessage.setSubject(emailDTO.getSubject());

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());
                helper.setText(htmlString, true);

                /*FileSystemResource file = new FileSystemResource(new File(fileToAttach));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment("logo.jpg", file);*/
            };
        /*MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                Template template = configuration.getTemplate("email-template.ftl");
                String htmlString = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDTO.getTo()));
                mimeMessage.setFrom(new InternetAddress(username));
                mimeMessage.setSubject(emailDTO.getSubject());
                mimeMessage.setText(htmlString);

                *//*FileSystemResource file = new FileSystemResource(new File(fileToAttach));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment("logo.jpg", file);*//*
            }
        };*/
            mailSender.send(preparator);
            mailResponse.setStatus(Boolean.TRUE);
            mailResponse.setMessage("Mail sent to " +emailDTO.getTo()+" successfully..");
        }catch (Exception ex) {
            mailResponse.setMessage("Mail sending fail.."+ex.getMessage());
            mailResponse.setStatus(Boolean.FALSE);
        }
        return mailResponse;
    }
}
