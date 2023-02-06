package demo.microservices.amqjms.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import demo.microservices.amqjms.config.JmsConfig;
import demo.microservices.amqjms.models.HolaMundoMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HolaSender {
	
	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;

	/**
	 * Rutina para enviar un mensaje a la cola de MQ
	 */
	@Scheduled(fixedRate = 5000)
	public void sendMessage() {
		/*
		HolaMundoMessage mensaje = HolaMundoMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hola Mundo!!")
				.build();
				
		jmsTemplate.convertAndSend(JmsConfig.HOLA_COLA, mensaje);
		*/
	}
	
	@Scheduled(fixedRate = 3000)
	public void sendAndReceiveMessage() throws JMSException {
		HolaMundoMessage datos = HolaMundoMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hola a todos!!")
				.build();
		
		Message receivedMsj = jmsTemplate.sendAndReceive(JmsConfig.SND_RCV_COLA, new MessageCreator() {			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = null;
				try {
					message = session.createTextMessage(objectMapper.writeValueAsString(datos));
					message.setStringProperty("_type","demo.microservices.amqjms.models.HolaMundoMessage");
					return message;
				} catch (JsonProcessingException | JMSException e) {
					e.printStackTrace();
					throw new JMSException("JMS Error");
				}
			}
		});
		
		System.out.println("=====> " + receivedMsj.getBody(String.class));
		
	}
}
