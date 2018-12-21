package com.baidu.message;

import com.baidu.message.messageCenter.MessageCenter.MessageCenter;
import com.baidu.message.messageCenter.client.EmailClient;
import com.baidu.message.messageCenter.server.EmailServer;

/**
 * email demo
 */
public class EmailClientDemo {
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
         * 创建邮件客户端，新建线程发送邮件
         */
        EmailClient emailClient = EmailClient.create();
        new Thread(emailClient).start();
    }
}
