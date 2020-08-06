package com.penguinforest.server;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);

        final SocketIOServer server = new SocketIOServer(config);
        server.addEventListener("chatevent", ChatObject.class, (client, data, ackRequest) -> {
            server.getBroadcastOperations().sendEvent("chatevent", data);
            System.out.println(data.getMessage());
        });

        server.start();
        System.out.println("Booting");

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();
    }
}
