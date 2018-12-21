package com.baidu.message.entity.common;

/**
 * 常量信息
 */
public class Constants {
    /**
     * 模拟发送邮件的数量
     */
    public static final int EMAIL_SEND_NUM = 20;

    /**
     * 模拟发送短信的数量
     */
    public static final int PHONE_SEND_NUM = 20;

    /**
     * 一分钟之内，令牌桶的最大容量。对应的是邮件服务器一分钟之内能发送的最大邮件数量
     */
    public static final int TOKEN_BUCKET_SIZE= 60;
}
