package com.baidu.message;

import org.junit.Test;

/**
 * Created by songfuxing on 2018/12/21.
 */
public class ClientTest {

    @Test
    public void testEmail() {
        EmailClientDemo.run();
    }

    @Test
    public void testPhoneMessage() {
        PhoneMessageClientDemo.run();
    }

}
