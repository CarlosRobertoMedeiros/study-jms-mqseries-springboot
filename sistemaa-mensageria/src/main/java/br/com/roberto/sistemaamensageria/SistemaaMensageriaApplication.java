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
//mvn package spring-boot:run

