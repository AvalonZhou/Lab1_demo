package com.magq.mq.controller;

import com.magq.mq.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息处理中心
 */
public class Broker {
    // 记录此类产生的logger
    private static final Logger logger = LoggerFactory.getLogger(Broker.class);
    public static final Map<String,List<String>> subscribeMap = new ConcurrentHashMap<>();
    public static final Map<String,List<String>> receivedMessagesMap = new ConcurrentHashMap<>();

    public static void handlePublish(String stageName,String message){
        // 对于新平台，添加key-value对
        if (!receivedMessagesMap.containsKey(stageName)){
            List<String> messageList = new ArrayList<>();
            messageList.add(message);
            receivedMessagesMap.put(stageName,messageList);
        }else{
            // 老平台，且没有重复发送消息，则直接在Value里面增加meg
            List<String> messageList = receivedMessagesMap.get(stageName);
            if(!messageList.contains(message)){
                messageList.add(message);
            }
        }
    }

    public static void handleSubscribe(String userName,String stageName){
        // 对于新平台，添加key-value对
        if (!subscribeMap.containsKey(userName)){
            List<String> stageList = new ArrayList<>();
            stageList.add(stageName);
            subscribeMap.put(userName,stageList);
        }else{
            // 老平台,且没有重复订阅,则直接在Value里面增加meg
            List<String> stageList = subscribeMap.get(userName);
            if(!stageList.contains(stageName)){
                stageList.add(stageName);
            }
        }
    }

    public static List<String> handleGet(String userName){
        List<String> result = new ArrayList<>();
        List<String> stageList = subscribeMap.get(userName);
        for(String stageName:stageList){
            List<String> messageList = receivedMessagesMap.get(stageName);
            for(String message:messageList){
                result.add(stageName+"\t"+message);
            }
        }
        return result;
    }

}

