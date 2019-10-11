package com.trilogy.cloudgamestorelevelupqueueconsumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    //Autowire Feign Client

    //constructor with client passed

    @RabbitListener(queues = CloudGameStoreLevelUpQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage() {
        //recieve message and pass via feign client
    }
}
