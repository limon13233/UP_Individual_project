package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.*;
import com.example.UP_PR2.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.*;

@Controller
//@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Employee emoEdit;
    @GetMapping("/ADD/employee")
    public String EmployeeAddView(Employee employee, Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("post", posts);

        return ("employee/employeeADD");}
    @PostMapping("/ADD/employee")
    public String EmployeeAdd(
           @Valid Employee employee,
           BindingResult bindingResult,
           Model model,
           @RequestParam String pname
    ) {
        if(bindingResult.hasErrors()){
            Iterable<Post> posts = postRepository.findAll();
            model.addAttribute("post", posts);
            return ("employee/employeeADD");}
        Post post = postRepository.findBypostname(pname);
        employee.setPost(post);
        employee.setRoles(Collections.singleton(Role.EMPLOYEE));
        employee.setActive(true);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        employeeRepository.save(employee);
        return ("redirect:/ADD");
    }


    @GetMapping("/Delete/user/{id}")
    public String EmployeeDelete(@PathVariable long id) {

        employeeRepository.deleteById(id);
        return("redirect:/Delete/user");
    }

    @GetMapping("Edit/user/{id}")
    public String EmployeeEdit(Model model, @PathVariable long id) {

        emoEdit = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", emoEdit);
        Iterable<Post> post = postRepository.findAll();
        model.addAttribute("post",post);
        return("employee/employee-edit");

    }
    @PostMapping("Edit/user/{id}")
    public String employeeEdit(@Valid Employee employee,
            BindingResult bindingResult,
                               Model model,
                               @RequestParam String pname
    ){
        employee.setPassword(emoEdit.getPassword());
        employee.setUsername(emoEdit.getUsername());
        employee.setActive(emoEdit.getActive());
        employee.setRoles(emoEdit.getRoles());
        if(bindingResult.hasErrors()){
            Iterable<Post> post = postRepository.findAll();
            model.addAttribute("post",post);
            return("employee/employee-edit");}
            Post post1 = postRepository.findBypostname(pname);
            employee.setPost(post1);
        employeeRepository.save(employee);

        return("redirect:/Edit/user");

    }


    @GetMapping("/employee/admin")
    public String AdminPanel(Model model) {

        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
                return ("employee/adminPanel");
    }

    @GetMapping("/employee/admin/Edit-role/{id}")
    public String EmployeeRoleEdit(Model model,
                                   @PathVariable long id) {

        Employee employee = employeeRepository.findById(id).orElseThrow();
        model.addAttribute("employee", employee);
        model.addAttribute("listRoles", Role.values());
        return("/employee/Edit-role");
    }

    @PostMapping("/employee/admin/Edit-role/{id}")
    public String EmployeeRoleEdit(@PathVariable long id,
                                   @RequestParam String[] roles) {

        Employee employee = employeeRepository.findById(id).orElseThrow();
        employee.getRoles().clear();

        for(String role: roles){
            employee.getRoles().add(Role.valueOf(role));
        }

        employeeRepository.save(employee);

        return("redirect:/employee/admin");
    }
}
