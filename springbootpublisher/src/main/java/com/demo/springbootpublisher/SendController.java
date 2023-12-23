package com.demo.springbootpublisher;

import jakarta.jms.ObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SendController {

    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/message/{message}")
    public String sendMessage(@PathVariable("message") String message) {
        jmsTemplate.send("demo", session -> {
            ObjectMessage message1 = session.createObjectMessage(message);
            return message1;
        });
        return message;
    }
}
