package br.com.roberto.sistemaamensageria.rest;

import java.nio.charset.StandardCharsets;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.mq.jms.MQQueue;

import br.com.roberto.sistemaamensageria.model.OrderRequest;
import br.com.roberto.sistemaamensageria.to.OrderRequestTo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("orders")
public class OrderController {

	@Autowired
	JmsTemplate JmsTemplate;

	@PostMapping
	public ResponseEntity<String> createOrder(@RequestBody OrderRequestTo orderRequestTo) throws JMSException {
		log.info("### 1 ### Sending order message '{}' to the queue", orderRequestTo);

		MQQueue orderedRequestQueue = new MQQueue("DEV.QUEUE.1");
		JmsTemplate.convertAndSend(orderedRequestQueue, orderRequestTo, textMessage -> {
			textMessage.setJMSCorrelationID(orderRequestTo.getCorrelationId());
			return textMessage;
		});

		return new ResponseEntity(orderRequestTo, HttpStatus.CREATED);
	}

	@GetMapping // this was just to show how to find a message by correlation Id
	public ResponseEntity<OrderRequest> findOrderByCorrelationId(@RequestParam String correlationId)
			throws JMSException {
		log.info("Looking for message '{}'", correlationId);
		String convertedId = bytesToHex(correlationId.getBytes());
		final String selectorExpression = String.format("JMSCorrelationID='ID:%s'", convertedId);
		final TextMessage responseMessage = (TextMessage) JmsTemplate.receiveSelected("DEV.QUEUE.1",
				selectorExpression);
		OrderRequest response = OrderRequest.builder().message(responseMessage.getText()).correlationId(correlationId)
				.build();
		System.out.println(response);
		return new ResponseEntity(response, HttpStatus.OK);
	}

	// You could use Apache Commons Codec library instead
	private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes();

	public static String bytesToHex(byte[] bytes) {
		byte[] hexChars = new byte[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars, StandardCharsets.UTF_8);
	}

}


