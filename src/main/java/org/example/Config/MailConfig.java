package org.example.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.yaml.snakeyaml.introspector.Property;

import java.util.Properties;

@Configuration
public class MailConfig {
   /* @Value("$(spring.mail.host)")
    private String host;

    @Value("$(spring.mail.port)")
    private String port;

    @Value("$(spring.mail.username)")
    private String username;

    @Value("$(spring.mail.password)")
    private String password;

    @Value("$(spring.mail.protocol)")
    private String protocol;

    @Value("$(mail.auth)")
    private String debug;*/

    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setPort(465);
        javaMailSender.setUsername("ilya260637@gmail.com");
        javaMailSender.setPassword("Fokin468279135");

        Properties property = javaMailSender.getJavaMailProperties();

        property.put("mail.transport.protocol", "smtp");
        property.put("mail.smtp.auth", "true");
        property.put("mail.smtp.starttls.enable", "true");
        property.put("mail.smtp.ssl.enable", "true");
        property.put("mail.debug", "true");

        /*property.setProperty("mail.transport.protocol", "smpts");
        property.setProperty("mail.debug", debug);*/

        return javaMailSender;
    }
}
