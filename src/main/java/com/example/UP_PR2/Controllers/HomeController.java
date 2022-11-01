package com.example.UP_PR2.Controllers;

import com.example.UP_PR2.Models.*;
import com.example.UP_PR2.Repositories.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.AbstractPlatformTransactionManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    AppliancesRepository appliancesRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    OfficeRepository officeRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/")
    public String index(Model model)
    {
        Iterable<Appliances> listAppliances = appliancesRepository.findAll();
        model.addAttribute(("list_appliances"), listAppliances);
        return ("index");
    }

    @GetMapping("/filter")
    public String EmployeeFilter(Model model,
                                 @RequestParam(name = "search") String name) {

        List<Appliances> appliances = appliancesRepository.findByNameContains(name);
        model.addAttribute("searchRes", appliances);
        return ("appliances/Filter");
    }

    //ADD
    @GetMapping("/ADD")
    public String add(Model model)
    {

        return ("ADD");
    }
    //EDIT

    @GetMapping("/Edit/appliances")
    public String EditAppliances(Model model)
    {
        Iterable<Appliances> listAppliances = appliancesRepository.findAll();
        model.addAttribute(("list_appliances"), listAppliances);
        return ("appliances/appliancesEdit");
    }

    @GetMapping("/Edit/user")
    public String EditEmployee(Model model)
    {
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
        return ("employee/employeeEdit");
    }
    @GetMapping("/Edit/issue")
    public String EditOffice(Model model)
    {
        Iterable<Office> listOffice = officeRepository.findAll();
        model.addAttribute(("list_office"), listOffice);
        return ("office/officeEdit");
    }

    @GetMapping("/Edit/post")
    public String EditPost(Model model)
    {
        Iterable<Post> listPost = postRepository.findAll();
        model.addAttribute(("list_post"), listPost);
        return ("post/postEdit");
    }
    @GetMapping("/Edit/provider")
    public String EditProvider(Model model)
    {
        Iterable<Provider> listProvider = providerRepository.findAll();
        model.addAttribute(("list_provider"), listProvider);
        return ("provider/providerEdit");
    }

    @GetMapping("/Edit")
    public String edit(Model model)
    {

        return ("Edit");
    }
    //DELETE
    @GetMapping("/Delete/appliances")
    public String DeleteAppliances(Model model)
    {
        Iterable<Appliances> listAppliances = appliancesRepository.findAll();
        model.addAttribute(("list_appliances"), listAppliances);
        return ("appliances/appliancesDelete");
    }
    @GetMapping("/Delete/user")
    public String DeleteEmployee(Model model)
    {
        Iterable<Employee> listEmployee = employeeRepository.findAll();
        model.addAttribute(("list_employee"), listEmployee);
        return ("employee/employeeDelete");
    }
    @GetMapping("/Delete/issue")
    public String DeleteOffice(Model model)
    {
        Iterable<Office> listOffice = officeRepository.findAll();
        model.addAttribute(("list_office"), listOffice);
        return ("office/officeDelete");
    }
    @GetMapping("/Delete/post")
    public String DeletePost(Model model)
    {
        Iterable<Post> listPost = postRepository.findAll();
        model.addAttribute(("list_post"), listPost);
        return ("post/postDelete");
    }
    @GetMapping("/Delete/provider")
    public String DeleteProvider(Model model)
    {
        Iterable<Provider> listProvider = providerRepository.findAll();
        model.addAttribute(("list_provider"), listProvider);
        return ("provider/providerDelete");
    }
    @GetMapping("/Delete")
    public String delete(Model model)
    {
        return ("Delete");
    }


    @GetMapping("/create-order/{id}")
    public String Buy1(Model model,
                       @PathVariable long id)
    {
        Date date = new Date();
        Appliances appliances = appliancesRepository.findById(id).orElseThrow();
        Order order = new Order();
        order.settotal_coast(appliances.getPrice());
        order.setcomplited_date(date);
        order.setappliances(appliances);
        //order.setuser(employee);
        orderRepository.save(order);
        model.addAttribute("order", order);
        Iterable<Office> offices = officeRepository.findAll();
        model.addAttribute("office",offices);
        return ("order/Order-create");
    }


    @PostMapping("/create-order/{id}")
    public String Buy(Model model,@RequestParam String complited_date,@RequestParam long id, @RequestParam String office) throws ParseException {
       String date = complited_date;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        Order order = orderRepository.findById(id).orElseThrow();
        order.setcomplited_date(dateFormat.parse(date));

        Office office1 = officeRepository.findByaddress(office);
        order.setpoint_of_issue(office1);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeRepository.findByusername(auth.getName());
        order.setuser(employee);
        orderRepository.save(order);
        return("redirect:/");
    }

    @GetMapping("/ViewOrder")
    public String ViewOrder(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = employeeRepository.findByusername(auth.getName());
        Iterable<Order> orders = orderRepository.findByuser(employee);
        model.addAttribute("list_orders",orders);
        return("order/ViewOrder") ;
    }
    @GetMapping("/ViewAllOrder")
    public String ViewAllOrder(Model model){
        Iterable<Order> orders = orderRepository.findAll();
        List<Order> trueOrder = new ArrayList<Order>();
        for (Order o : orders) {
            if(o.getuser()!=null)
            {
                trueOrder.add(o);
            }
        }
        if(trueOrder!=null){
        model.addAttribute("list_orders",trueOrder);}
        return("order/ViewAllOrder") ;
    }
}
