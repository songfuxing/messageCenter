package com.baidu.message.client;

import com.baidu.message.entity.pojo.Message;
import com.baidu.message.messagecenter.MessageCenter;

/**
 * 接入消息中心的client 都需要实现该抽象类
 * 生产者
 */
public abstract class Client {
    MessageCenter messageCenter;

    /**
     * 向消息中心发送消息
     * @param message 消息
     */
    public abstract void send(Message message);

    /**
     * 每个不同的client都需要将自己发送的数据类型和相应的处理服务器注册到消息中心
     */
    public abstract void registry();

    protected Client() {
        this.messageCenter = MessageCenter.getInstance();
    }


}
