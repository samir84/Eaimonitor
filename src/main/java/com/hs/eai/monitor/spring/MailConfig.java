package com.hs.eai.monitor.spring;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.hs.eai.monitor.common.SendMail;

@Configuration
public class MailConfig {


	@Autowired
	private Environment env;
	
	private static final String MAIL_TRANSPORT_PROTOCOL = "mailTransportProtocol";
	private static final String MAIL_HOST = "mailHost";
	private static final String MAIL_USER = "mailUser";
	private static final String MAIL_PASSWORD = "mailPassword";
	private static final String MAIL_PATH_ATTACHMENT = "mailPathAttachment";
	private static final String MAIL_CONTENT_TYPE = "mailContentType";
	private static final String MAIL_SUPPORT = "supportEmail";
	
	@Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        
        Properties javaMailProperties = new Properties();
        javaMailProperties.setProperty("mail.transport.protocol", env.getRequiredProperty(MAIL_TRANSPORT_PROTOCOL));
        javaMailProperties.setProperty("mail.host", env.getRequiredProperty(MAIL_HOST));
        javaMailProperties.setProperty("mail.user", env.getRequiredProperty(MAIL_USER));
        javaMailProperties.setProperty("mail.password", env.getRequiredProperty(MAIL_PASSWORD));
        
        javaMailSender.setJavaMailProperties(javaMailProperties);
        return javaMailSender;
    }
	
	@Bean
    public SimpleMailMessage simpleMailMessageFromSupport() {
       SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
       simpleMailMessage.setFrom(env.getRequiredProperty(MAIL_SUPPORT));
       return simpleMailMessage;
    }

	
}
