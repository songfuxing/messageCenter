package com.baidu.message;

import com.baidu.message.messageCenter.MessageCenter.MessageCenter;
import com.baidu.message.messageCenter.client.PhoneMessageClient;
import com.baidu.message.messageCenter.server.EmailServer;

/**
 * 短信 demo
 */
public class PhoneMessageClientDemo {
    public static void run() {
        /**
         * 启动消息中心服务
         */
        MessageCenter.getInstance().start();

        /**
         * 启动邮件服务
         */
        EmailServer.getInstance().start();


        /**
         * 创建短信客户端，发送短信
         */
        PhoneMessageClient phoneMessageClient = PhoneMessageClient.create();
        new Thread(phoneMessageClient).start();

    }
}
