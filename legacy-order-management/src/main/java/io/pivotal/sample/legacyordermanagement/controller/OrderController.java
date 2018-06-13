package io.pivotal.sample.legacyordermanagement.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private static final Log LOG = LogFactory.getLog(OrderController.class);

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public String placeOrder(@RequestBody String order) {
        LOG.info(String.format("Received order [%s].", order));
        return "{\"result\": \"successfully ordered\"}";
    }
}