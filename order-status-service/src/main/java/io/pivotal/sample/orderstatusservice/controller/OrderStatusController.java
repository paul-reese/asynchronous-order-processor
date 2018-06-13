package io.pivotal.sample.orderstatusservice.controller;

import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.sample.orderstatusservice.OrderStatus;
import io.pivotal.sample.orderstatusservice.repository.OrderStatusRepository;

@RestController
public class OrderStatusController {

    private static final Log LOG = LogFactory.getLog(OrderStatusController.class);

    @Autowired
    OrderStatusRepository orderStatusRepository;

    @RequestMapping(value = "/orderStatus/{id}", method = RequestMethod.GET)
    public String getOrderStatus(@PathVariable Long id) {
        Optional<OrderStatus> orderStatus = orderStatusRepository.findById(id);
        if (orderStatus.isPresent()) {
            LOG.debug(String.format("Order with id [%d] = [%s].", 
                id, orderStatus.toString()));
            return "{\"id\": " + id.toString() + ", \"status\": " + orderStatus.get().getStatus() + "}";

        } else {
            LOG.debug(String.format("Order status with id [%d] not found.", id));
            return "not found";
        }
    }
}