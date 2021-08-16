package br.gov.caixa.siati.spring.ibmmq;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@SpringBootApplication
@EnableJms
public class IbmMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(IbmMqApplication.class, args);
	}

	@Bean
	public ConnectionFactory connectionFactory() {

		MQConnectionFactory factory = new MQConnectionFactory();
		try {
			factory.setHostName("localhost");
			factory.setPort(1414);
			factory.setQueueManager("QM1");
			factory.setChannel("DEV.APP.SVRCONN");
			factory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
			factory.setClientReconnectOptions(WMQConstants.WMQ_CLIENT_RECONNECT);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return factory;
	}
}
