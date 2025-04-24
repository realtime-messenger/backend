package com.example.backend.config;

import com.example.backend.security.STOMPChannelInterceptor;
import com.example.backend.security.WebSocketInterceptor;
import com.example.backend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.config.StompBrokerRelayRegistration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    private final JwtService jwtService;

    private final String rabbitmqHost;
    private final String rabbitmqPort;
    private final String rabbitmqUsername;
    private final String rabbitmqPassword;


    @Autowired
    public WebSocketConfiguration(
            JwtService jwtService,
            @Value("${rabbitmq.host}") String rabbitmqHost,
            @Value("${rabbitmq.port}") String rabbitmqPort,
            @Value("${rabbitmq.username}") String rabbitmqUsername,
            @Value("${rabbitmq.password}") String rabbitmqPassword
    ) {

        this.jwtService = jwtService;
        this.rabbitmqHost = rabbitmqHost;
        this.rabbitmqPort = rabbitmqPort;
        this.rabbitmqUsername = rabbitmqUsername;
        this.rabbitmqPassword = rabbitmqPassword;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        StompBrokerRelayRegistration relayRegistration = config.enableStompBrokerRelay(
                "/topic/",
                "/queue/"
        );

        relayRegistration.setRelayHost(this.rabbitmqHost);
        relayRegistration.setRelayPort(Integer.parseInt(this.rabbitmqPort));
        relayRegistration.setClientLogin(this.rabbitmqUsername);
        relayRegistration.setClientPasscode(this.rabbitmqPassword);
        relayRegistration.setSystemLogin(this.rabbitmqUsername);
        relayRegistration.setSystemPasscode(this.rabbitmqPassword);

        relayRegistration.setSystemHeartbeatReceiveInterval(10000);
        relayRegistration.setSystemHeartbeatSendInterval(10000);

        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .addInterceptors(new WebSocketInterceptor(jwtService));
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new STOMPChannelInterceptor());
    }
}