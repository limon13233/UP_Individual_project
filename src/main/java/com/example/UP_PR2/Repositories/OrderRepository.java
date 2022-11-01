package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Appliances;
import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Models.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

public Iterable<Order> findByuser(Employee user);
}
