package com.example.UP_PR2.Models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@Entity
@Table(name = "appliances")
public class Appliances {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Обязательное поле")
    @Size(max = 50,message = "Поле не должно быть больше 50 символов")
    private String name;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(1)
    private int price;
    @NotNull(message = "Поле не должно быть пустым")
    @Min(1)
    private int wight;
    @NotBlank(message = "Обязательное поле")
    @Size(max = 20,message = "Поле не должно быть больше 10 символов")
    private String size;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private Provider provider;
    @OneToMany(mappedBy = "appliances", fetch = FetchType.EAGER)
    private Collection<Order> orders;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) { this.name = name; }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public int getWight() {
        return wight;
    }
    public void setWight(int wight) {
        this.wight = wight;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

    public Provider getProvider() {
        return provider;
    }
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Collection<Order> getOrders() {
        return orders;
    }
    public void setOrders(Collection<Order> Orders) {
        this.orders = orders;
    }

    public String getName_provider(){return provider.getNamecompany();}



    public Appliances() { }

    public Appliances(String name, int price,int wight,String size, Provider provider) {
        this.name = name;
        this.price = price;
        this.wight = wight;
        this.size = size;
        this.provider = provider;
    }
}
