package demo.microservices.amqjms.sender;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import demo.microservices.amqjms.config.JmsConfig;
import demo.microservices.amqjms.models.HolaMundoMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HolaSender {
	
	private final JmsTemplate jmsTemplate;

	/**
	 * Rutina para enviar un mensaje a la cola de MQ
	 */
	@Scheduled(fixedRate = 5000)
	public void sendMessage() {
		HolaMundoMessage mensaje = HolaMundoMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hola Mundo!!")
				.build();
				
		jmsTemplate.convertAndSend(JmsConfig.HOLA_COLA, mensaje);
	}
}
