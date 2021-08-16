package br.com.roberto.sistemaamensageria.jms.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsOperations;

import br.com.roberto.sistemaamensageria.to.OrderRequestTo;

//@Component
public class JMSConsumer {
	
	@Autowired
	JmsOperations jmsOperations;
	
	@Value("${resposta.sispi}")
	String queue;
	
	//@JmsListener(destination = "${envio.sispi}", containerFactory="jsaFactory")
	public void receive(OrderRequestTo orderRequestTo){
		jmsOperations.receiveAndConvert(queue);
		//System.out.println("Recieved Message: " + orderRequestTo);
	}

}
