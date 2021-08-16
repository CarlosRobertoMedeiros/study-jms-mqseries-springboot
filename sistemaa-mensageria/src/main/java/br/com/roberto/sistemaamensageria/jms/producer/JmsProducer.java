package br.com.roberto.sistemaamensageria.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import br.com.roberto.sistemaamensageria.to.OrderRequestTo;

@Component
public class JmsProducer {
	
	@Autowired
	JmsTemplate jmsTemplate;
	
	@Value("${envio.sispi}")
	String queue;
	
	public void send(OrderRequestTo orderRequestTo){
		jmsTemplate.convertAndSend(queue, orderRequestTo);
	}

}
