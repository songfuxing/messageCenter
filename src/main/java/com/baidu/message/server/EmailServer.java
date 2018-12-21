package com.baidu.message.server;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.message.entity.pojo.Email;
import com.baidu.message.entity.pojo.Message;

/**
 * 邮件处理服务器
 */
public class EmailServer implements Server, Runnable {
    private static Logger logger = LoggerFactory.getLogger(EmailServer.class);
    public static EmailServer emailServer;

    /**
     * 邮件服务器的缓存队列
     * 邮件服务器qps有限，因此先放到缓存队列中，然后从队列中取
     */
    private static BlockingQueue<Email> queue = new LinkedBlockingQueue<>();

    /**
     * 令牌桶，从缓存中获取邮件的时候，需要先去令牌桶获取token，如果获取token失败，则等待
     */
    private TokenBucket tokenBucket;

    private EmailServer() {

    }

    public static EmailServer getInstance() {
        if (emailServer == null) {
            synchronized (EmailServer.class) {
                if (emailServer == null) {
                    emailServer = new EmailServer();
                }
            }
        }
        return emailServer;
    }

    /**
     * @param message
     */
    @Override
    public void receive(Message message) {
        Email email = (Email) message;
        queue.add(email);
    }

    @Override
    public void start() {
        tokenBucket = TokenBucket.create();
        new Thread(emailServer).start();
        new Thread(tokenBucket).start();
    }

    @Override
    public void run() {
        logger.info("EMAIL SEVER START");
        try {
            while (true) {
                tokenBucket.consumeToken();
                Email email = queue.take();
                logger.info("Email Server Receive Email, Message:{}", email.getText());
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

}
