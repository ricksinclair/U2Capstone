package com.trilogy.cloudgamestorelevelupqueueconsumer;

import com.trilogy.cloudgamestorelevelupqueueconsumer.model.LevelUp;
import com.trilogy.cloudgamestorelevelupqueueconsumer.util.feign.LevelUpServiceClient;
import com.trilogy.cloudgamestorelevelupqueueconsumer.util.messages.LevelUpEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MessageListener {

    //Autowire Feign Client
    @Autowired
    private LevelUpServiceClient levelUpServiceClient;

    //constructor with client passed
    MessageListener(LevelUpServiceClient levelUpServiceClient) {this.levelUpServiceClient = levelUpServiceClient;}

    @RabbitListener(queues = CloudGameStoreLevelUpQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(LevelUpEntry msg) {
        //recieve message and pass via feign client
        System.out.println(msg.toString());
        LevelUp levelUp = new LevelUp();
        levelUp.setLevelUpId(msg.getLevelUpId());
        levelUp.setCustomerId(msg.getCustomerId());
        levelUp.setPoints(msg.getPoints());
        levelUp.setMemberDate(LocalDate.parse(msg.getMemberDate()));

        //Placeholder for logic to either create entry or update existing
        if(levelUp.getLevelUpId() == 0) {
            levelUpServiceClient.saveLevelUp(levelUp);
        } else {
            levelUpServiceClient.updateLevelUp(levelUp);
        }
    }
}
