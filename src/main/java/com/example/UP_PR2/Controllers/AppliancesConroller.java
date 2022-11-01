package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Appliances;
import com.example.UP_PR2.Models.Employee;
import com.example.UP_PR2.Models.Post;
import com.example.UP_PR2.Models.Provider;
import com.example.UP_PR2.Repositories.AppliancesRepository;
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
public class AppliancesConroller {

    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    AppliancesRepository appliancesRepository;

    @GetMapping("/ADD/appliances")
    public String AppliancesAddView(Appliances appliances, Model model)
    {
        Iterable<Provider> provider = providerRepository.findAll();
        model.addAttribute("provider", provider);
        return ("appliances/appliances_add");
    }
    @PostMapping("/ADD/appliances")
    public String AppliancesAdd(
            @Valid Appliances appliances,
            BindingResult bindingResult,
            Model model,
            @RequestParam String pprovider
    ) {
        if (bindingResult.hasErrors()){
            Iterable<Provider> provider = providerRepository.findAll();
            model.addAttribute("provider", provider);
            return ("appliances/appliances_add");}
        Provider provider1 = providerRepository.findByNamecompany(pprovider);
        appliances.setProvider(provider1);
        appliancesRepository.save(appliances);
        return ("redirect:/ADD");
    }

    @GetMapping("/Delete/appliances/{id}")
    public String AppliancesDelete(@PathVariable long id) {

        appliancesRepository.deleteById(id);
        return("redirect:/Delete/appliances");
    }


    @GetMapping("Edit/appliances/{id}")
    public String AppliancesEdit(Model model, @PathVariable long id) {

        Appliances tovar = appliancesRepository.findById(id).orElseThrow();
        Iterable<Provider> provider = providerRepository.findAll();
        model.addAttribute("provider", provider);
        model.addAttribute("appliances", tovar);
        return("appliances/appliances-edit");

    }
    @PostMapping("Edit/appliances/{id}")
    public String employeeEdit(@Valid Appliances appliances,
                               BindingResult bindingResult,
                               Model model,
                               @RequestParam String pprovider
    ) {
        if (bindingResult.hasErrors()){
            Iterable<Provider> provider = providerRepository.findAll();
            model.addAttribute("provider", provider);
            return ("appliances/appliances-edit");}
        Provider provider1 = providerRepository.findByNamecompany(pprovider);
        appliances.setProvider(provider1);
        appliancesRepository.save(appliances);
        return ("redirect:/Edit/appliances");
    }
}
