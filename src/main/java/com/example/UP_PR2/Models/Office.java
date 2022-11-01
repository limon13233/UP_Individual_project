package com.example.UP_PR2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "point_of_issue")
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Обязательное поле")
    private String address;
    @NotBlank(message = "Обязательное поле")
    private String schedule;

    @OneToMany(mappedBy = "point_of_issue", fetch = FetchType.EAGER)
    private Collection<Order> orders;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) { this.address = address; }

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public Collection<Order> getOrders() {
        return orders;
    }
    public void seteOrders(Collection<Order> Orders) {
        this.orders = orders;
    }

    public Office() { }

    public Office(String address, String schedule) {
        this.address = address;
        this.schedule = schedule;

    }

}
