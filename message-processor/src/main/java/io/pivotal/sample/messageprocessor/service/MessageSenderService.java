package io.pivotal.sample.messageprocessor.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageSenderService {

    @Autowired
    RabbitMessagingTemplate messageTemplate;

    private static final Log LOG = LogFactory.getLog(MessageSenderService.class);

    public void sendMessage(String message) {
        LOG.debug(String.format("Sending message [%s].", message));
        messageTemplate.convertAndSend("result-queue", message);
    }
}