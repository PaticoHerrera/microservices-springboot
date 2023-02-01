package demo.microservices.amqjms.listener;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
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

	@JmsListener(destination = JmsConfig.HOLA_COLA)
	public void listen(@Payload HolaMundoMessage holaMundoMessage, @Headers MessageHeaders header, Message message) {
		
		System.out.println("=====> " + holaMundoMessage);
	}
}
