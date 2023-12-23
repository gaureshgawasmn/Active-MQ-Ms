## Overview of the Project

### Active Mq (activemq) module
    
This module has basic publisher and consumer(subscriber) classes to demonstrate how to create queue and topics.

It also has an example of json object message publisher and subscriber. 

To create multiple consumer use the program argument as first argument will 
be client id and second argument as consumer name. 

### Spring Boot Publisher (springbootpublisher) module

This is a spring boot project running on 8081 port to publish the message to demo queue.

URL to publish the message http://localhost:8081/api/v1/message/{message}

### Spring Boot Consumer (springbootconsumer) module

This is a spring boot project running at 8082 port to having jmsListener for queue "demo" which will listen to the message published at demo queue and consume it.

### Additonal setup done for active mq config
Added the plugin for broker in activemq.xml at the end of broker, copy and paste the snippet from Authentication-Plugin.txt

Change the credentials.properties to accomodate the user and password you have configured for active mq

activemq.username={username set in activemq.xml}

activemq.password={password set in activemq.xml}