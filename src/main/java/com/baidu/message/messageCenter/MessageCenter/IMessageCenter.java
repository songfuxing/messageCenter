package com.baidu.message.messageCenter.MessageCenter;

import com.baidu.message.entity.pojo.Message;
import com.baidu.message.entity.common.MessageType;
import com.baidu.message.messageCenter.server.Server;

/**
 * 消息中心对外接口
 */
public interface IMessageCenter {
    /**
     * 提供给client，发送消息
     * @param message
     */
    public void addMessage(Message message);

    /**
     * 提供给client，注册消息类型和相应的处理器实例
     * @param type
     * @param server
     */
    public void register(MessageType type, Server server);

    /**
     * 启动消息中心服务
     */
    public void start();
}
