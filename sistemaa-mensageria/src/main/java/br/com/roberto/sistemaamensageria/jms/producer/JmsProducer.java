package br.com.roberto.sistemaamensageria.jms.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsOperations;
import org.springframework.stereotype.Component;

import br.com.roberto.sistemaamensageria.to.OrderRequestTo;

@Component
public class JmsProducer {
	
	@Autowired
	JmsOperations jmsOperations;
	
	@Value("${envio.sispi}")
	String queue;
	
	public void send(OrderRequestTo orderRequestTo){
		jmsOperations.convertAndSend(queue, orderRequestTo);
	}

}
