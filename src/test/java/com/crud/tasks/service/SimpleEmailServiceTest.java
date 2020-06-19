package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendMail() {

        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test message", "");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        if (mail.getToCc().contains("@")) {mailMessage.setTo(mail.getToCc());}
        mailMessage.setTo(mail.getSubject());
        mailMessage.setTo(mail.getMessage());

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);
        }

    }
