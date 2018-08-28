package org.myazure.service.impl;

import javax.mail.MessagingException;

import org.myazure.service.MailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MailServiceImpl implements MailService {

	public void sendMailBySMTP(String subject, String context, String recivers,
			String sender) throws MessagingException {
		// TODO Auto-generated method stub

	}

	public void sendMail(String subject, String context, String recivers)
			throws MessagingException {
		// TODO Auto-generated method stub

	}

	public void sendMail(String host, String username, String password,
			String subject, String context, String recivers, String sender)
			throws MessagingException {
		// TODO Auto-generated method stub

	}
}
