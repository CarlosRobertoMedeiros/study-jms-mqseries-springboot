package br.gov.caixa.siati.spring.ibmmq.jms;

import java.util.Map;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import br.gov.caixa.siati.spring.ibmmq.jms.listener.Pessoa;

@Component
public class IbmMqConsumer {

	//@JmsListener(destination = "${mq.siati-req-solicitacao.qname}")
	@JmsListener(destination = "${envio.sispi}")
	public void receiveMessage(@Headers Map<String, Object> messageAttributes, @Payload String msg) {
		Request<Pessoa> request = Request.fromJsonString(msg);
		Pessoa obj = request.getPayload();
		System.out.println(obj);
	}

}
