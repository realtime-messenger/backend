package com.example.backend.config;

import com.proto.onlineTracker.OnlineTrackerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfiguration {

    private final String protoHost;
    private final int protoPort;


    public GrpcClientConfiguration(
            @Value("${proto.host}") String protoHost,
            @Value("${proto.port}") int protoPort
    ) {
        System.out.println(protoHost);
        System.out.println(protoPort);

        this.protoHost = protoHost;
        this.protoPort = protoPort;
    }


    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder
                .forAddress(protoHost, protoPort) // Укажите адрес и порт gRPC сервера
                .usePlaintext() // Отключение TLS для тестирования
                .build();
    }

    @Bean
    public OnlineTrackerGrpc.OnlineTrackerBlockingStub newBlockingStub (ManagedChannel channel) {
        return OnlineTrackerGrpc.newBlockingStub(channel);
    }
}