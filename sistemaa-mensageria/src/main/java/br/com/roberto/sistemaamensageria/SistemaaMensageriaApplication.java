package br.com.roberto.sistemaamensageria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * TODO: 
 *   - Corrigir as filas com o nome padrão dos sistemas
 *   - Implementar o listener jms para envio de maneira automática
 *   - Criar os converters para json dos objetos de entrada
 * @author carlos
 *
 */



@SpringBootApplication
@EnableJms
public class SistemaaMensageriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaaMensageriaApplication.class, args);
	}

}

//Reference links
// https://www.baeldung.com/jackson-deserialization
// https://ozenero.com/spring-jms-activemq-send-java-object-messages-activemq-server-specially-bi-directional-relationship-java-objects
// https://doc.akka.io/docs/alpakka/current/jms/ibm-mq.html