package com.uisarel.institucion.security;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.uisarel.institucion.utils.ConstantApp;

@Configuration
public class SenderMailConfig {

	@Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
       
        mailSender.setHost(ConstantApp.HOST_SERVICE_EMAIL);
        mailSender.setPort(ConstantApp.PORT_SERVICE_EMAIL);

        mailSender.setUsername(ConstantApp.USER_SERVICE_EMAIL);
        mailSender.setPassword(ConstantApp.PASS_SERVICE_EMAIL);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", false);
        return mailSender;
    }
}
