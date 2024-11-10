package com.magq.mq.client;

import com.magq.mq.config.Config;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class Stage {
    private final String name;

    public Stage(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //生产消息
    public void produce(String message) throws Exception {
        //本地的的 BrokerServer.SERVICE_PORT 创建SOCKET
        Socket socket = new Socket(InetAddress.getLocalHost(), Config.SERVICE_PORT);
        // try with resource写法，以便于关闭IO流
        try (PrintWriter out = new PrintWriter(socket.getOutputStream())) {
            out.println(Config.PUBLISH + "\t"+this.getName()+"\t"+message);
            out.flush();
        }
    }
}
