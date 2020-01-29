package com.zhangwj;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName MqTest
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/29 19:55
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MqApplication.class)
public class MqTest {
    @Autowired
    RabbitTemplate template;

    @Test
    public void send(){
        template.convertAndSend("zhangwj","张无忌");
    }

    /**
     * 发布订阅，往交换机发送消息，交换机将消息发送到每个绑定的队列中
     */
    @Test
    public void fanout(){
        template.convertAndSend("ex1",null,"ceshi");
    }

}
