package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Appliances;
import com.example.UP_PR2.Models.Order;
import com.example.UP_PR2.Models.Provider;
import com.example.UP_PR2.Repositories.AppliancesRepository;
import com.example.UP_PR2.Repositories.OrderRepository;
import com.example.UP_PR2.Repositories.ProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class OrderController {

    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    AppliancesRepository appliancesRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/Delete/order/{id}")
    public String IssueDelete(@PathVariable long id) {

        orderRepository.deleteById(id);
        return("redirect:/ViewOrder");
    }
}
