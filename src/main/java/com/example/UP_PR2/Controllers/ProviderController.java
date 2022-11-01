package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Appliances;
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
public class ProviderController {

    @Autowired
    ProviderRepository providerRepository;

    @GetMapping("/ADD/provider")
    public String ProviderAddView(Provider provider, Model model)
    {
        return ("provider/ProviderADD");
    }
    @PostMapping("/ADD/provider")
    public String AppliancesAdd(
            @Valid Provider provider,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()){
            return ("provider/ProviderAdd");}
        providerRepository.save(provider);
        return ("redirect:/ADD");
    }
    @GetMapping("/Delete/provider/{id}")
    public String ProviderDelete(@PathVariable long id) {
        providerRepository.deleteById(id);
        return("redirect:/Delete/provider");
    }

    @GetMapping("/Edit/provider/{id}")
    public String IssueEdit(Model model, @PathVariable long id) {

        Provider provider = providerRepository.findById(id).orElseThrow();
        model.addAttribute("provider", provider);
        return("provider/provider-edit");

    }
    @PostMapping("/Edit/provider/{id}")
    public String IssueEdit(@Valid Provider provider,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ("provider/provider-edit");}
        providerRepository.save(provider);
        return ("redirect:/Edit/provider");
    }
}
