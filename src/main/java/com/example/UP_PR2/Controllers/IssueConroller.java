package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.Appliances;
import com.example.UP_PR2.Models.Office;
import com.example.UP_PR2.Models.Provider;
import com.example.UP_PR2.Repositories.AppliancesRepository;
import com.example.UP_PR2.Repositories.OfficeRepository;
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
public class IssueConroller {

    @Autowired
    OfficeRepository officeRepository;

    @GetMapping("/ADD/Point_of_issue")
    public String OfficeAdd(Office office, Model model)
    {
        return ("office/OfficeADD");
    }

    @PostMapping("/ADD/Point_of_issue")
    public String OfficeAdd(@Valid Office office,
                            BindingResult bindingResult,
                            Model model
    ) {
        if (bindingResult.hasErrors()){
            return ("office/OfficeADD");}
        officeRepository.save(office);
        return ("redirect:/ADD");
    }
    @GetMapping("/Delete/issue/{id}")
    public String IssueDelete(@PathVariable long id) {

        officeRepository.deleteById(id);
        return("redirect:/Delete/issue");
    }

    @GetMapping("/Edit/issue/{id}")
    public String IssueEdit(Model model, @PathVariable long id) {

        Office office = officeRepository.findById(id).orElseThrow();
        model.addAttribute("office", office);
        return("office/office-edit");

    }
    @PostMapping("/Edit/issue/{id}")
    public String IssueEdit(@Valid Office office,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return ("office/office-edit");}
        officeRepository.save(office);
        return ("redirect:/Edit/issue");
    }
}
