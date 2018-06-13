package io.pivotal.sample.orders.service;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderQueryService {

    private static final Log LOG = LogFactory.getLog(OrderQueryService.class);

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    EurekaClient eurekaClient;

    @HystrixCommand(fallbackMethod = "failedOrderQuery")
    public String queryOrder(Long orderId) {
        LOG.debug(String.format("Querying order from response service for order id [%d]", 
            orderId));
        InstanceInfo instance = eurekaClient.getNextServerFromEureka(
            "ORDER-STATUS-SERVICE", false);
        String serviceUri = instance.getHomePageUrl() + "orderStatus/";
        LOG.debug(String.format("URI for order status service = [%s]", serviceUri));
        String status = restTemplate.getForObject(
            serviceUri + orderId,
            String.class);
        return status;

    }

    public String failedOrderQuery(Long orderId) {
        LOG.debug(String.format("Order query system unavailable. Order id = [%d]", 
        orderId));
        return "{\"status\": \"failed\", \"message\": \"order query system unavailable\"}";
    }
}