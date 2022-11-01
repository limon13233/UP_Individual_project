package com.example.UP_PR2.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.util.Collection;

@Entity
@Table(name = "provider")
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Обязательное поле")
    @Size(max = 100,message = "Поле не должно быть больше 100 символов")
    private String namecompany;
    @NotBlank(message = "Обязательное поле")
    @Size(max=12,min = 12, message = "ИНН содержит 12 символов")
    @Pattern(regexp = "^[0-9]{12}$", message = "ИНН содержит 12 символов")
    private String inn;
    @Pattern(regexp = "^[+][0-9][(][0-9][0-9][0-9][)][0-9][0-9][0-9][-][0-9][0-9][-][0-9][0-9]$", message = "Телефон должна быть записана в формате 8(***)-***-**-**")
    private String phone;
    @NotBlank(message = "Обязательное поле")
    @Size(max = 100,message = "Поле не должно быть больше 100 символов")
    private String ur_address;
    @NotBlank(message = "Обязательное поле")
    @Size(max = 200,message = "Поле не должно быть больше 200 символов")
    private String information_of_boss;
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    private Collection<Appliances> Appliances;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNamecompany() {
        return namecompany;
    }
    public void setNamecompany(String name_company) {
        this.namecompany = name_company;
    }

    public String getInn() {
        return inn;
    }
    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUr_address() {
        return ur_address;
    }
    public void setUr_address(String ur_address) {
        this.ur_address = ur_address;
    }

    public String getInformation_of_boss() {
        return information_of_boss;
    }
    public void setInformation_of_boss(String information_of_boss) {
        this.information_of_boss = information_of_boss;
    }

    public Collection<Appliances> getAppliances() {
        return Appliances;
    }
    public void setAppliances(Collection<Appliances> Appliances) {
        this.Appliances = Appliances;
    }

    public Provider() { }

    public Provider(String name_company, String inn,String phone,String ur_address, String information_of_boss) {
        this.namecompany = name_company;
        this.inn = inn;
        this.phone = phone;
        this.ur_address = ur_address;
        this.information_of_boss = information_of_boss;
    }
}
