package com.example.UP_PR2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "[order]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private Date complited_date;
    @NotNull
    private int total_coast;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Appliances appliances;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Employee user;
    @ManyToOne(optional = true, cascade = CascadeType.DETACH)
    private Office point_of_issue;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getcomplited_date() {
        return complited_date;
    }
    public void setcomplited_date(Date complited_date) { this.complited_date = complited_date; }

    public int gettotal_coast() {
        return total_coast;
    }
    public void settotal_coast(int total_coast) { this.total_coast = total_coast; }

    public Appliances getappliances() {
        return appliances;
    }
    public void setappliances(Appliances appliances) { this.appliances = appliances; }

    public Employee getuser() {
        return user;
    }
    public void setuser(Employee user) { this.user = user; }

    public Office getpoint_of_issue() {
        return point_of_issue;
    }
    public void setpoint_of_issue(Office point_of_issue) { this.point_of_issue = point_of_issue; }

    public Order() { }

    public Order(Date complited_date, int total_coast, Appliances appliances,Employee user,Office point_of_issue) {
        this.complited_date = complited_date;
        this.total_coast = total_coast;
        this.appliances=appliances;
        this.user=user;
        this.point_of_issue=point_of_issue;

    }
}
