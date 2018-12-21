package com.baidu.message.entity.pojo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baidu.message.entity.common.MessageType;

/**
 * 短信类
 */
public class PhoneMessage  implements Message {
    /**
     * 发信人手机号
     */
    private String sender;

    /**
     * 收信人手机号列表
     */
    private List<String> addr;

    /**
     * 发信内容
     */
    private String text;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getAddr() {
        return addr;
    }

    public void setAddr(List<String> addr) {
        this.addr = addr;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public MessageType getType() {
        return MessageType.PHONE;
    }

    public static PhoneMessage create(String text) {
        PhoneMessage phoneMessage = new PhoneMessage();
        phoneMessage.setAddr(Stream.of("songfuxing").collect(Collectors.toList()));
        phoneMessage.setSender("songfuxing");
        phoneMessage.setText(text);
        return phoneMessage;
    }
}
