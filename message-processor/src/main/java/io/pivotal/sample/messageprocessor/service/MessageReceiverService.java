package io.pivotal.sample.messageprocessor.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageReceiverService {

    private static final Log LOG = LogFactory.getLog(MessageReceiverService.class);

    @Autowired
    OrderManagementService orderManagement;

    @Autowired
    MessageSenderService messageSender;

    @RabbitListener(queues = "order-queue")
    public void receiveMessage(String message) {
        LOG.debug(String.format("Received message [%s].", message));
        String result = orderManagement.placeOrder(message);
        messageSender.sendMessage(result);
    }
}