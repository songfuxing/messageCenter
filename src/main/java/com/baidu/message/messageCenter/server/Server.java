package com.baidu.message.messageCenter.server;

import com.baidu.message.entity.pojo.Message;

/**
 * 每个服务器都要实现
 * 消费者
 */
public interface Server {
    /**
     * 处理信息
     */
    public void receive(Message message);

    /**
     * 启动邮件服务
     */
    public void start();
}
