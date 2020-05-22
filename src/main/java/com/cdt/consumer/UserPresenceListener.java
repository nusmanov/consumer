package com.cdt.consumer;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserPresenceListener {
    Logger logger = LoggerFactory.getLogger(UserPresenceListener.class);
    private List<String> availableUsers = new ArrayList<>();

    @RabbitListener(queues = "user-presence")
    public void handle(UserGoesOnlineMessage userGoesOnlineMessage) {
        this.availableUsers.add(userGoesOnlineMessage.getUser());
        logger.info("User added: {} / total {}", userGoesOnlineMessage.getUser(), availableUsers.size());

    }

    public List<String> getAvailableUsers() {
        return availableUsers;
    }
}
