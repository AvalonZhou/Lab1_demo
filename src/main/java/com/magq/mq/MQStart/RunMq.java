package com.magq.mq.MQStart;

import com.magq.mq.config.Config;
import com.magq.mq.server.BrkServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.net.ServerSocket;

/**
 * 启动加载MQ
 */

@Component
public class RunMq implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(RunMq.class);

    @Override
    public void run(ApplicationArguments applicationArguments){
        logger.info("启动加载MQ  -- START");
        try {
            ServerSocket server = new ServerSocket(Config.SERVICE_PORT);
            while (true) {
                BrkServer BrkServer = new BrkServer(server.accept());
                new Thread(BrkServer).start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
