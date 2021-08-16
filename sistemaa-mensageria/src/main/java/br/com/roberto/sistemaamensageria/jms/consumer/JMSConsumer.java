package br.com.roberto.sistemaamensageria.jms.consumer;

import org.springframework.jms.annotation.JmsListener;

import br.com.roberto.sistemaamensageria.to.OrderRequestTo;

//@Component
public class JMSConsumer {
	
	@JmsListener(destination = "${envio.sispi}", containerFactory="jsaFactory")
	public void receive(OrderRequestTo orderRequestTo){
		System.out.println("Recieved Message: " + orderRequestTo);
	}

}
