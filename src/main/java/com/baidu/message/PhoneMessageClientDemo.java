/**
 * 【认证方式二】使用非实际工作中的代码进行认证
 题目背景：
 （1）系统中经常产生各种消息，这些消息需要以各种方式（邮件、短信、hi...）通知给用户；
 （2）消息产生的频率可能很高，由于邮件服务器/短信服务器等的限制，对外发送需排队发送。

 题目描述：请设计并实现“消息中心“模块，主要功能：
 （1）支持各种消息（现有邮件、短信、hi）发送，可支持消息种类扩充；
 （2）邮件服务器等每分钟只能发送M条信息，请考虑缓存/队列等的使用，对消息发送进行限流。设计的时候，请考虑性能因素。

 题目要求：
 （1）接口设计友好，可以自解释；
 （2）严格遵守公司代码规范；
 （3）单测覆盖率80%，并提供使用该“消息中心”模块的完整工程以及client demo。

 其他：
 （1）邮件、短信、hi消息发送模拟实现即可。
 */
package com.baidu.message;

import com.baidu.message.messageCenter.MessageCenter.MessageCenter;
import com.baidu.message.messageCenter.client.PhoneMessageClient;
import com.baidu.message.messageCenter.server.EmailServer;

/**
 * 调用demo
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
