package com.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.concurrent.TimeUnit;


public class Publisher {
    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // create destination using the queue name that is created or else new queue will be created with the provided name
            Destination destination = session.createQueue("demo");

            MessageProducer producer = session.createProducer(destination);
            for (int i = 0; i < 10; i++) {
                TextMessage textMessage = session.createTextMessage("Message for Queue " + i);
                producer.send(textMessage);
                System.out.println("Message published to queue : " + i);
                TimeUnit.SECONDS.sleep(1);
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
