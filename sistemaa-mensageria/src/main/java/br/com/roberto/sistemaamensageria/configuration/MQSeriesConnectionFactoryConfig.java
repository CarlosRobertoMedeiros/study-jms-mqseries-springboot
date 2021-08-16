package br.com.roberto.sistemaamensageria.configuration;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.ibm.mq.jms.MQQueueConnectionFactory;

@Configuration
public class MQSeriesConnectionFactoryConfig {

	// @Value("${jsa.activemq.broker.url}")
	@Value("${ibm.mq.connName}")
	String brokerUrl;

	@Value("${ibm.mq.user}")
	String userName;

	@Value("${ibm.mq.password}")
	String password;

	/*
	 * Initial ConnectionFactory
	 */
	@Bean
	public ConnectionFactory connectionFactory() throws JMSException {
		MQQueueConnectionFactory connectionFactory = new MQQueueConnectionFactory();

		connectionFactory.setHostName(brokerUrl);
		connectionFactory.setPort(1414);
		connectionFactory.setQueueManager("QM1");
		connectionFactory.setChannel("DEV.APP.SVRCONN");

		return connectionFactory;
	}

	@Bean // Serialize message content to json using TextMessage
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		return converter;
	}

	/*
	 * Used for Receiving Message
	 */
	@Bean
	public JmsListenerContainerFactory<?> jsaFactory(ConnectionFactory connectionFactory,
			DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setMessageConverter(jacksonJmsMessageConverter());
		configurer.configure(factory, connectionFactory);
		return factory;
	}

	/*
	 * Used for Sending Messages.
	 */
	@Bean
	public JmsTemplate jmsTemplate() throws JMSException {
		JmsTemplate template = new JmsTemplate();
		template.setMessageConverter(jacksonJmsMessageConverter());
		template.setConnectionFactory(connectionFactory());
		return template;
	}

}
