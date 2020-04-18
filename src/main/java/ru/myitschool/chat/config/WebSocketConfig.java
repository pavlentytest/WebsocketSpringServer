package ru.myitschool.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker // <=  включает обработку сообщений WebSocket, поддерживаемую брокером сообщений

public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // ws://127.0.0.1/im

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/im").addInterceptors(new Handshake())
                .withSockJS(); // <= обязательно withSockJS - чтобы можно было отправлять JSON на сокет сервер

    }

}
