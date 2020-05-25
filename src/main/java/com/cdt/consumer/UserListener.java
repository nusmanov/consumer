package com.cdt.consumer;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserListener {
    Logger logger = LoggerFactory.getLogger(UserListener.class);
    private final List<UserDTO> availableUsers = new ArrayList<>();

    @RabbitListener(queues = "user-presence-queue")
    public void handle(UserDTO userDTO) {
        this.availableUsers.add(userDTO);
        logger.info("User added: {} / total {}", userDTO, availableUsers.size());

    }

    public List<UserDTO> getAvailableUsers() {
        return availableUsers;
    }
}
