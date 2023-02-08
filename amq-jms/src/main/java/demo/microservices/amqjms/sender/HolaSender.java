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
	
	// preconfigurado que usamos para comunicarnos con el mq
	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;
	
	@Scheduled(fixedRate = 4000)
	public void sendMessage() {
		/*
		HolaMundoMessage message = HolaMundoMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hola Mundo!")
				.build();
		jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
		*/
	}

	@Scheduled(fixedRate =4000)
	public void sendAndReceiveMessage() throws JMSException {
		
		HolaMundoMessage payloadMessage = HolaMundoMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Hola!")
				.build();
		
		Message receivedMsj = jmsTemplate.sendAndReceive(JmsConfig.SND_RCV_COLA, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = null;
				try {
					message = session.createTextMessage(objectMapper.writeValueAsString(payloadMessage));
					message.setStringProperty("_type", "demo.microservices.amqjms.models.HolaMundoMessage");
					System.out.println("Sending message...");
					return message;
				} catch (JsonProcessingException | JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new JMSException("JMS Error");
				}				
			}
		});
		
		System.out.println("======> " + receivedMsj.getBody(String.class));
		
		
	}
	
}
