package br.gov.caixa.siati.spring.ibmmq.jms.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.gov.caixa.siati.spring.ibmmq.jms.IbmMqProducer;

@Component
@Order(0)
class MyApplicationListener implements ApplicationListener<ApplicationReadyEvent> {

	//@Value("${mq.siati-req-solicitacao.qname}")
	@Value("${envio.sispi}")
	private String qName;

	@Autowired
	private IbmMqProducer producer;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		producer.send(qName, new Pessoa(), UUID.randomUUID().toString());
	}

}