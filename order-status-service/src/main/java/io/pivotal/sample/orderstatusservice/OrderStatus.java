package io.pivotal.sample.orderstatusservice;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date date;

    @Column
    private String status;

    public void setId(Long id) {this.id = id;}
    public Long getId() {return this.id;}

    public void setDate(Date date) {this.date = date;}
    public Date getDate() {return this.date;}

    public void setStatus(String status) {this.status = status;}
    public String getStatus() {return this.status;}

}