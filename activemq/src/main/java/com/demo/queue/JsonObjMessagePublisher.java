package com.demo.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONObject;

import javax.jms.*;

public class JsonObjMessagePublisher {

    public static void main(String[] args) {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        try {
            Connection connection = factory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // create destination using the queue name that is created or else new queue will be created with the provided name
            Destination destination = session.createQueue("jsonDemo");

            MessageProducer producer = session.createProducer(destination);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("start_date", 20230101);
            jsonObject.put("end_date", 20230331);
            jsonObject.put("person_id", 1);
            jsonObject.put("email", "xyz@com");

            TextMessage message = session.createTextMessage(jsonObject.toString());
            producer.send(message);

            session.close();
            connection.close();
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
