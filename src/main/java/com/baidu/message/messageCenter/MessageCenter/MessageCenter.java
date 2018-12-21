package com.baidu.message.messageCenter.MessageCenter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.message.entity.pojo.Message;
import com.baidu.message.entity.common.MessageType;
import com.baidu.message.messageCenter.server.Server;

/**
 * 消息中心类
 */
public class MessageCenter implements Runnable, IMessageCenter {
    private static Logger logger = LoggerFactory.getLogger(MessageCenter.class);
    /**
     * 缓存队列
     */
    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();

    /**
     * 注册表，把数据类型和相关服务器类型对应
     */
    private static Map<MessageType, Server> registry = new HashMap<>();

    private MessageCenter() {
    }

    private static MessageCenter messageCenter;

    /**
     * 单例
     *
     * @return
     */
    public static MessageCenter getInstance() {
        if (messageCenter == null) {
            synchronized(MessageCenter.class) {
                messageCenter = new MessageCenter();
            }
        }
        return messageCenter;
    }

    public void addMessage(Message message) {
        queue.add(message);
    }

    @Override
    public void register(MessageType type, Server server) {
        if (!registry.containsKey(type)){
            registry.put(type, server);
        }
        logger.info("New client registry to message center, type:{}", type);
    }

    @Override
    public void start() {
       new Thread(messageCenter).start();
    }

    @Override
    public void run() {
        logger.info("MESSAGE CENTER START");
        Message message = null;
        try {
            while(true) {
                message = queue.take();
                registry.get(message.getType()).receive(message);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }
}
