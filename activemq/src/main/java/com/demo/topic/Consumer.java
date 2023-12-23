package com.demo.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer {
    public static void main(String[] args) {
        // added so that we can start multiple consumer with program argument
        String clientId = "1";
        String consumerName = "Consumer-1";
        if (args.length > 1) {
            clientId = args[0];
            consumerName = args[1];
        }

        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            connection.setClientID(clientId);
            connection.start();// this is required while consumer only not publisher

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create destination using the queue name that is created or else new queue will be created with the provided name
            Topic topic = session.createTopic("Demo-Topic");

            // create consumer to consume the msg from destination
            MessageConsumer consumer = session.createDurableSubscriber(topic, consumerName);

            // listener will keep listening and if any msg published it will print it
            consumer.setMessageListener(message -> {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("Message Received :" + textMessage.getText());
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
