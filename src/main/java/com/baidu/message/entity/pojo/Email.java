package com.baidu.message.entity.pojo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.baidu.message.entity.common.MessageType;

/**
 * 邮件类
 */

public class Email implements Message {
    /**
     * 发件人邮箱
     */
    private String sender;

    /**
     * 收件人邮箱列表
     */
    private List<String> addr;

    /**
     * 抄送人邮箱列表
     */
    private List<String> copyAddr;

    /**
     * 邮件正文
     */
    private String text;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getAddr() {
        return addr;
    }

    public void setAddr(List<String> addr) {
        this.addr = addr;
    }

    public List<String> getCopyAddr() {
        return copyAddr;
    }

    public void setCopyAddr(List<String> copyAddr) {
        this.copyAddr = copyAddr;
    }

    /**
     * 生产默认的邮件
     * @return
     */
    public static Email create(String text) {
        Email email = new Email();
        email.setSender("system");
        email.setAddr(Stream.of("songfuxing").collect(Collectors.toList()));
        email.setSender("songfuxing");
        email.setText(text);
        return email;
    }

    @Override
    public MessageType getType() {
        return MessageType.EMAIL;
    }
}
