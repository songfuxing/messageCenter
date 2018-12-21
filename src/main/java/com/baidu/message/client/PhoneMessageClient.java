package com.baidu.message.client;

import org.slf4j.LoggerFactory;

import com.baidu.message.entity.common.Constants;
import com.baidu.message.entity.common.MessageType;
import com.baidu.message.entity.pojo.Message;
import com.baidu.message.entity.pojo.PhoneMessage;
import com.baidu.message.server.PhoneMessageServer;

/**
 * 发送邮件客户端
 */
public class PhoneMessageClient extends Client implements Runnable {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(PhoneMessageClient.class);

    @Override
    public void send(Message message) {
        messageCenter.addMessage(message);
    }

    @Override
    public void registry() {
        messageCenter.register(MessageType.PHONE, PhoneMessageServer.getInstance());
    }

    private PhoneMessageClient() {
    }

    /**
     * 静态工厂方法产生客户端实例
     *
     * @return
     */
    public static PhoneMessageClient create() {
        PhoneMessageClient phoneMessageClient = new PhoneMessageClient();
        phoneMessageClient.registry();
        return phoneMessageClient;
    }

    /**
     * 使用多线程发送邮件, 模拟用户多次发送信息
     */
    @Override
    public void run() {
        for (int i = 0; i < Constants.PHONE_SEND_NUM; i++) {
            try {
                Thread.sleep(5L);
                String msg = "Phone Message" + i;
                logger.info("Phone Message Client Produced New Message: {}", msg);
                PhoneMessage phoneMessage = PhoneMessage.create(msg);
                send(phoneMessage);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
