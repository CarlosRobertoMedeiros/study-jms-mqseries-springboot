package br.gov.caixa.siati.spring.ibmmq.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class IbmMqProducer {

	private JmsTemplate jmsTemplate;

	@Autowired
	public IbmMqProducer(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public <T> void send(String queue, T entity, String correlationID) {
		String requestString = Request.Builder.get().correlationID(correlationID).payload(entity).build().toJsonString();
		this.jmsTemplate.convertAndSend(queue, requestString);
	}

}
