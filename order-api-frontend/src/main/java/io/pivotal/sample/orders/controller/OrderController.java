package io.pivotal.sample.orders.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pivotal.sample.orders.service.MessageSenderService;
import io.pivotal.sample.orders.service.OrderQueryService;

@RestController
public class OrderController {

    private static final Log LOG = LogFactory.getLog(OrderController.class);

    @Autowired
    MessageSenderService messageSender;

    @Autowired
    OrderQueryService orderQuery;

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String placeOrder(@RequestBody String order) {
        LOG.debug(String.format("Received request to place order [%s].", order));
        messageSender.sendMessage(order);
        return "{\"status\": \"order submitted for processing\"}";
    }

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public String queryOrder(@PathVariable Long orderId) {
        LOG.debug(String.format("Querying order for id [%d]", orderId));
        return orderQuery.queryOrder(orderId);
    }

}