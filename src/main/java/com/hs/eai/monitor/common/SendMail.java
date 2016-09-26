package com.hs.eai.monitor.common;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class SendMail {

	private static final Logger logger = LoggerFactory.getLogger(SendMail.class);

	private static final String MAIL_TRANSPORT_PROTOCOL = "mailTransportProtocol";
	private static final String MAIL_HOST = "mailHost";
	private static final String MAIL_USER = "mailUser";
	private static final String MAIL_PASSWORD = "mailPassword";
	private static final String MAIL_PATH_ATTACHMENT = "mailPathAttachment";
	private static final String MAIL_CONTENT_TYPE = "mailContentType";

	@Autowired
	private Environment env;

	public GenericResponse send(Mail mail) {

		logger.debug("Start sending message");
		GenericResponse genericResponse = null ;
		try {
			String pathAttachment;

			Properties properties = new Properties();
			properties.setProperty("mail.transport.protocol", env.getRequiredProperty(MAIL_TRANSPORT_PROTOCOL));
			properties.setProperty("mail.host", env.getRequiredProperty(MAIL_HOST));
			properties.setProperty("mail.user", env.getRequiredProperty(MAIL_USER));
			properties.setProperty("mail.password", env.getRequiredProperty(MAIL_PASSWORD));
			pathAttachment = env.getRequiredProperty(MAIL_PATH_ATTACHMENT);

			Session mailSession = Session.getDefaultInstance(properties, null);

			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);

			// subject
			message.setSubject(mail.getSubject());

			// from
			message.addFrom(new InternetAddress[] { new InternetAddress(mail.getFrom()) });

			// message
			Multipart multipart = new MimeMultipart("alternative");
			MimeBodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(mail.getContent(), env.getRequiredProperty(MAIL_CONTENT_TYPE));
			multipart.addBodyPart(messageBodyPart);

			// attachment
			if (mail.getAttachmentName() != null && mail.getAttachmentContent() != null
					&& !mail.getAttachmentName().equals("")) {
				OutputStream targetFile = new FileOutputStream(pathAttachment + mail.getAttachmentName());
				targetFile.write(mail.getAttachmentContent());
				targetFile.close();

				messageBodyPart = new MimeBodyPart();
				DataSource source = new FileDataSource(pathAttachment + mail.getAttachmentName());
				messageBodyPart.setDataHandler(new DataHandler(source));
				messageBodyPart.setFileName(mail.getAttachmentName());
				multipart.addBodyPart(messageBodyPart);
			}

			message.setContent(multipart);

			// to
			if (mail.getTo() != null) {
				for (String address : mail.getTo()) {
					if (address != null) {
						message.addRecipient(Message.RecipientType.TO, new InternetAddress(address));
					}
				}
			}

			// cc
			if (mail.getCc() != null) {
				for (String address : mail.getCc()) {
					if (address != null) {
						message.addRecipient(Message.RecipientType.CC, new InternetAddress(address));
					}
				}
			}

			// bcc
			if (mail.getBcc() != null) {
				for (String address : mail.getBcc()) {
					if (address != null) {
						message.addRecipient(Message.RecipientType.BCC, new InternetAddress(address));
					}
				}
			}

			transport.connect();

			transport.sendMessage(message, message.getAllRecipients());

			transport.close();
			
			genericResponse.setMessage("Email was success sended to: "+message.getAllRecipients());
			genericResponse.setObject(null);
			genericResponse.setStatus("SUCCES");

		} catch (Exception e) {
			genericResponse.setObject(null);
			genericResponse.setStatus("FAIL");
			genericResponse.setMessage("message not send due :"+e.getMessage());
			e.printStackTrace();
		} 
		
		return genericResponse;
	}
}