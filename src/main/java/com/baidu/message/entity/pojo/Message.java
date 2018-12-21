package com.baidu.message.entity.pojo;

import com.baidu.message.entity.common.MessageType;

/**
 * 消息中心能够处理的消息抽象类
 */
public  interface Message {
    public MessageType getType();
}
