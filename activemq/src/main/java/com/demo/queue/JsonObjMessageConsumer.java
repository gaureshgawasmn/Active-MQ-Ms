package com.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class JsonObjMessageConsumer {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            connection.start();// this is required while consumer only not publisher

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

            // create destination using the queue name that is created or else new queue will be created with the provided name
            Destination destination = session.createQueue("jsonDemo");

            // create consumer to consume the msg from destination
            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener(message -> {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("Message Received :" + textMessage.getText());
                    // if Session.CLIENT_ACKNOWLEDGE is used we have to acknowledge manually else in auto not needed below code
                    // textMessage.acknowledge();
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
