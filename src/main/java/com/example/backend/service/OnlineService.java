package com.example.backend.service;

import com.proto.onlineTracker.ConnectRequest;
import com.proto.onlineTracker.DisconnectRequest;
import com.proto.onlineTracker.OnlineTrackerGrpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineService {

    private final OnlineTrackerGrpc.OnlineTrackerBlockingStub onlineTrackerServiceStub;

    @Autowired
    public OnlineService(OnlineTrackerGrpc.OnlineTrackerBlockingStub onlineTrackerServiceStub) {
        this.onlineTrackerServiceStub = onlineTrackerServiceStub;
    }


    public void pingUserOnline (
            long userId,
            String sessionId
    ) {
        ConnectRequest request = ConnectRequest.newBuilder()
                .setUserId(userId)
                .setSessionId(sessionId).build();

        var res = onlineTrackerServiceStub.onConnect(request);
    }

    public void pingUserOffline (
            long userId,
            String sessionId
    ) {
        DisconnectRequest request = DisconnectRequest.newBuilder()
                .setUserId(userId)
                .setSessionId(sessionId).build();

        var res = onlineTrackerServiceStub.onDisconnect(request);
    }
}
