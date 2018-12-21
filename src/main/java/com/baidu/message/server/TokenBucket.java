package com.baidu.message.server;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.message.entity.common.Constants;

/**
 * 用令牌桶控制处理邮件的速率
 */
public class TokenBucket implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(TokenBucket.class);
    /**
     * 将令牌桶的容量设置为邮件服务器一分钟内最大的处理量
     */
    static final int BUCKET_SIZE = Constants.TOKEN_BUCKET_SIZE;

    private static BlockingQueue<String> bucket = new ArrayBlockingQueue<>(BUCKET_SIZE);
    private static TokenBucket tokenBucket = null;

    public static TokenBucket create() {
        if (tokenBucket == null) {
            synchronized (TokenBucket.class) {
                if (tokenBucket == null) {
                    tokenBucket = new TokenBucket();
                }
            }
        }
        return tokenBucket;
    }

    /**
     * 消费令牌
     */
    public void consumeToken() throws InterruptedException {
        bucket.take();
        logger.info("consume token success");
    }

    @Override
    public void run() {
        double qps = (double) BUCKET_SIZE / 60;
        long gap = (long) (1 / qps) * 1000;
        while (true) {
            try {
                bucket.put("token");
                logger.info("bucket receive a token, bucket size: {}", bucket.size());
                Thread.sleep(gap);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}