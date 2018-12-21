package com.baidu.message.client;

import org.slf4j.LoggerFactory;

import com.baidu.message.entity.common.Constants;
import com.baidu.message.entity.common.MessageType;
import com.baidu.message.entity.pojo.Email;
import com.baidu.message.entity.pojo.Message;
import com.baidu.message.server.EmailServer;

/**
 * 发送邮件客户端
 */
public class EmailClient extends Client implements Runnable {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(EmailClient.class);

    @Override
    public void send(Message message) {
        messageCenter.addMessage(message);
    }

    @Override
    public void registry() {
        messageCenter.register(MessageType.EMAIL, EmailServer.getInstance());
    }

    private EmailClient() {
    }

    /**
     * 静态工厂方法产生客户端实例
     *
     * @return
     */
    public static EmailClient create() {
        EmailClient emailClient = new EmailClient();
        emailClient.registry();
        return emailClient;
    }

    /**
     * 使用多线程发送邮件, 模拟用户多次发送信息
     */
    @Override
    public void run() {
        for (int i = 0; i < Constants.EMAIL_SEND_NUM; i++) {
            try {
                Thread.sleep(5L);
                String msg = "Email" + i;
                logger.info("Email Client Produced New Email: {}", msg);
                Email email = Email.create(msg);
                send(email);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
