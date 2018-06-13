package io.pivotal.sample.orderstatusservice.service;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.pivotal.sample.orderstatusservice.OrderStatus;
import io.pivotal.sample.orderstatusservice.repository.OrderStatusRepository;

@Service
public class MessageReceiverService {

    private static final Log LOG = LogFactory.getLog(MessageReceiverService.class);

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @RabbitListener(queues = "result-queue")
    public void receiveMessage(String message) {
        LOG.debug(String.format("Received message [%s].", message));
        OrderStatus orderStatus = new OrderStatus();
        orderStatus.setDate(new Date());
        orderStatus.setStatus(message);
        orderStatusRepository.save(orderStatus);
        LOG.debug(String.format("Saved order status [%s].", 
            orderStatus.toString()));    
    }
}