package br.com.roberto.sistemaamensageria.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderService {
	
	@JmsListener(destination = "DEV.QUEUE.1")
    public void receive(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("### 4 ### Order Service received message response : {} with correlation id: {}",
                textMessage.getText(), textMessage.getJMSCorrelationID());

        // do some business logic here, like updating the order in the database
    }
	
	@JmsListener(destination = "DEV.QUEUE.2")
    public void enviar(Message message) throws JMSException {
        TextMessage textMessage = (TextMessage) message;
        log.info("### 4 ### Order Service received message response : {} with correlation id: {}",
                textMessage.getText(), textMessage.getJMSCorrelationID());

        // do some business logic here, like updating the order in the database
        //https://www.youtube.com/watch?v=-9XkQawOOec
    }
	
	
	

}
