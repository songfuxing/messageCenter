package com.baidu.message;

import com.baidu.message.client.PhoneMessageClient;
import com.baidu.message.messagecenter.MessageCenter;
import com.baidu.message.server.PhoneMessageServer;

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
         * 启动短信服务
         */
        PhoneMessageServer.getInstance().start();

        /**
         * 创建短信客户端，发送短信
         */
        PhoneMessageClient phoneMessageClient = PhoneMessageClient.create();
        new Thread(phoneMessageClient).start();

    }

    public static void main(String[] args) {
        run();
    }
}
