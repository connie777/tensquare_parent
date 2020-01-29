package com.zhangwj.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName Consumer1
 * @Description TODO
 * @Author Silence
 * @Date 2020/1/29 20:07
 * @Version 1.0
 **/
@Component
@RabbitListener(queues = "zhangwj")
public class Consumer1 {
    @RabbitHandler
    public void receive(String msg){
        System.out.println(msg);
    }
}
