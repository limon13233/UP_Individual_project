package com.example.UP_PR2.Repositories;

import com.example.UP_PR2.Models.Provider;
import org.springframework.data.repository.CrudRepository;

public interface ProviderRepository extends CrudRepository<Provider, Long> {

    public Provider findByNamecompany(String name_company);
}
