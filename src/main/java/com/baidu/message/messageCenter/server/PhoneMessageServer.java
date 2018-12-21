package com.baidu.message.messageCenter.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.message.entity.pojo.Message;
import com.baidu.message.entity.pojo.PhoneMessage;

/**
 * 短信处理服务器
 */
public class PhoneMessageServer implements Server, Runnable {
    private static Logger logger = LoggerFactory.getLogger(PhoneMessageServer.class);
    public static PhoneMessageServer phoneMessageServer;

    private static BlockingQueue<PhoneMessage> queue = new LinkedBlockingQueue<>();

    private PhoneMessageServer() {

    }

    public static PhoneMessageServer getInstance() {
        if (phoneMessageServer == null) {
            synchronized(PhoneMessageServer.class) {
                if (phoneMessageServer == null) {
                    phoneMessageServer = new PhoneMessageServer();
                }
            }
        }
        return phoneMessageServer;
    }

    /**
     * @param message
     */
    @Override
    public void receive(Message message) {
        PhoneMessage phoneMessage = (PhoneMessage) message;
        queue.add(phoneMessage);
    }

    @Override
    public void start() {
        new Thread(phoneMessageServer).start();
    }

    @Override
    public void run() {
        logger.info("PHONE MESSAGE SEVER START");
        try {
            while (true) {
                PhoneMessage phoneMessage = queue.take();
                logger.info("Phone Server Receive Phone Message, Message:{}", phoneMessage.getText());
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
