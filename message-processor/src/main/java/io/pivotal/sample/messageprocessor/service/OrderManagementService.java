package io.pivotal.sample.messageprocessor.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.pivotal.sample.messageprocessor.LegacyOrderManagementServiceProperties;

@Service
public class OrderManagementService {

    private static final Log LOG = LogFactory.getLog(OrderManagementService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LegacyOrderManagementServiceProperties orderManagementProperties;

    // You can enable the circuit breaker here, by uncommenting the 
    // @HystrixCommand attribute.  But it's probably preferable to not, 
    // since this method will throw an error, and upstream callers should
    // leave messages on the queue for retry in that case.
    //@HystrixCommand(fallbackMethod = "failedOrderSystem")
    public String placeOrder(String order) {
        
        LOG.debug(String.format("Order management service URL = [%s].",
            orderManagementProperties.getUrl()));
        String result = restTemplate.postForObject(
            orderManagementProperties.getUrl() + "/order", order, String.class);
        LOG.debug(String.format("Order posted = [%s], result = [%s].", 
            order, result));
        return result;

    }

    public String failedOrderSystem(String order) {
        LOG.debug(String.format("Legacy order system unavailable. Order = [%s]", 
            order));
        return "{\"status\": \"failed\", \"message\": \"order system unavailable\"}";
    }
}