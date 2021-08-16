package br.com.roberto.sistemaamensageria.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;

@Configuration
public class MQSeriesConnectionFactoryConfig {

	
	@Value("${ibm.mq.queueManager}")
	private String queueManager;

	// @Value("${jsa.activemq.broker.url}")
	@Value("${ibm.mq.connName}")
	private String brokerUrl;
	
	@Value("${ibm.mq.channel}")
	private String channel;
	
	@Value("${ibm.mq.port}")
	private Integer port;

	@Value("${ibm.mq.user}")
	private String username;

	@Value("${ibm.mq.password}")
	private String password;
	
	@Value("${ibm.mq.Receive-timeout}")
	private Long receiveTimeout;
	
	@Bean
	public MQQueueConnectionFactory mqQueueConnectionFactory() {
	    MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
	    
	    try {
	    	mqQueueConnectionFactory.setHostName(brokerUrl);
	    	mqQueueConnectionFactory.setPort(port);
	    	mqQueueConnectionFactory.setChannel(channel);
	    	mqQueueConnectionFactory.setQueueManager(queueManager);
	    	mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
	        mqQueueConnectionFactory.setClientReconnectOptions(WMQConstants.WMQ_CLIENT_RECONNECT);
	        mqQueueConnectionFactory.setCCSID(1208);//For Linux //For Windows 1381
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	    return mqQueueConnectionFactory;
	}
	
	
	@Bean
	UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter(MQQueueConnectionFactory mqQueueConnectionFactory) {
	    UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter = new UserCredentialsConnectionFactoryAdapter();
	    userCredentialsConnectionFactoryAdapter.setUsername(username);
	    userCredentialsConnectionFactoryAdapter.setPassword(password);
	    userCredentialsConnectionFactoryAdapter.setTargetConnectionFactory(mqQueueConnectionFactory);
	    return userCredentialsConnectionFactoryAdapter;
	}
	
	@Bean
	@Primary
	public CachingConnectionFactory cachingConnectionFactory(UserCredentialsConnectionFactoryAdapter userCredentialsConnectionFactoryAdapter) {
	    CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
	    cachingConnectionFactory.setTargetConnectionFactory(userCredentialsConnectionFactoryAdapter);
	    cachingConnectionFactory.setSessionCacheSize(500);
	    cachingConnectionFactory.setReconnectOnException(true);
	    return cachingConnectionFactory;
	}
	

	/*Uncomment for Work with Transactional
	 * 
	@Bean
	public PlatformTransactionManager jmsTransactionManager(CachingConnectionFactory cachingConnectionFactory) {
	    JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
	    jmsTransactionManager.setConnectionFactory(cachingConnectionFactory);
	    return jmsTransactionManager;
	}
	*/
	
	@Bean
	public JmsOperations jmsOperations(CachingConnectionFactory cachingConnectionFactory) {
	    JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
	    jmsTemplate.setReceiveTimeout(receiveTimeout);
	    return jmsTemplate;
	}	
	

}
