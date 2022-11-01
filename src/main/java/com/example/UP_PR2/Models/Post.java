package com.example.UP_PR2.Models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Collection;

@Entity
@Table(name = "Post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Обязательное поле")
    private String postname;
    @NotBlank(message = "Обязательное поле")
    private String salary;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private Collection<Employee> employees;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getPostname() {
        return postname;
    }
    public void setPostname(String postName) {
        this.postname = postName;
    }

    public String getSalary() {
        return salary;
    }
    public void setSalary(String Salary) {
        this.salary = Salary;
    }

    public Collection<Employee> getemployees() {
        return employees;
    }
    public void setemployees(Collection<Employee> employees) {
        this.employees = employees;
    }



    public Post() { }

    public Post(String postName, String salary) {
        this.postname = postName;
        this.salary = salary;

    }
}
