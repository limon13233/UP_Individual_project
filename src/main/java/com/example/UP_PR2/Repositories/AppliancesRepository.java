package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Appliances;
import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Models.Office;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppliancesRepository extends CrudRepository<Appliances, Long> {

    public Appliances findByName(String name);
    public List<Appliances> findByNameContains(String surname);
}
