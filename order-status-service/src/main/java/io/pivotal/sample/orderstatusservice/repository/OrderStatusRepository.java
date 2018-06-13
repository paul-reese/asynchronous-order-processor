package io.pivotal.sample.orderstatusservice.repository;

import org.springframework.data.repository.CrudRepository;

import io.pivotal.sample.orderstatusservice.OrderStatus;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Long> {

    public OrderStatus findByStatus(String status);
}