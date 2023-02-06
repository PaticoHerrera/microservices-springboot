package demo.microservices.amqjms.listener;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import demo.microservices.amqjms.config.JmsConfig;
import demo.microservices.amqjms.models.HolaMundoMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HolaListener {
	
	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = JmsConfig.HOLA_COLA)
	public void listen(@Payload HolaMundoMessage holaMundoMessage, @Headers MessageHeaders header, Message message) {
		
		//System.out.println("=====> " + holaMundoMessage);
	}
	
	@JmsListener(destination = JmsConfig.SND_RCV_COLA)
	public void listenAndReply(@Payload HolaMundoMessage holaMundoMessage, @Headers MessageHeaders header, Message message) throws JMSException {
		HolaMundoMessage payloadMessage = HolaMundoMessage
				.builder()
				.id(UUID.randomUUID())
				.message("Como te va!!!!")
				.build();
		jmsTemplate.convertAndSend(message.getJMSReplyTo(), payloadMessage);
	}
}
