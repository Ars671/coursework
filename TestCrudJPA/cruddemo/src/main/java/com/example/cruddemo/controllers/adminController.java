package com.example.cruddemo.controllers;

import com.example.cruddemo.models.*;
import com.example.cruddemo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;


@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class adminController {

    private final PostRepository postRepository;
    private final CotegoryRepository cotegoryRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseRepository warehouseRepository;
    private final ElectronicsRepository electronicsRepository;
    private final CantractRepository cantractRepository;
    private final SupplyRepository supplyRepository;
    private final EmployeeRepository employeeRepository;
    private final LogsRepository logsRepository;

    public String searchstring1 = "";
    public String searchstring = "";

    @Autowired
    public adminController(PostRepository postRepository, CotegoryRepository cotegoryRepository, SupplierRepository supplierRepository, WarehouseRepository warehouseRepository, ElectronicsRepository electronicsRepository, CantractRepository cantractRepository, SupplyRepository supplyRepository, EmployeeRepository employeeRepository, LogsRepository logsRepository) {
        this.postRepository = postRepository;
        this.cotegoryRepository = cotegoryRepository;
        this.supplierRepository = supplierRepository;
        this.warehouseRepository = warehouseRepository;
        this.electronicsRepository = electronicsRepository;
        this.cantractRepository = cantractRepository;
        this.supplyRepository = supplyRepository;
        this.employeeRepository = employeeRepository;
        this.logsRepository = logsRepository;
    }

    @GetMapping("/admin")
    public String admin(Model model)
    {
        if(searchstring.equals(""))
        {
            model.addAttribute("posts", postRepository.findAll());
        }
        else
        {
            model.addAttribute("posts", postRepository.findByPostname(searchstring));
        }
        model.addAttribute("cotegorys", cotegoryRepository.findAll());
        model.addAttribute("suppliers", supplierRepository.findAll());
        model.addAttribute("warehouses", warehouseRepository.findAll());
        model.addAttribute("electronicss", electronicsRepository.findAll());
        model.addAttribute("cantracts", cantractRepository.findAll());
        model.addAttribute("supplys", supplyRepository.findAll());
        model.addAttribute("employees", employeeRepository.findAll());
        model.addAttribute("user_list", userRepository.findAll());
        if(searchstring1.equals(""))
        {
            model.addAttribute("logss", logsRepository.findAll());
        }
        else
        {
            model.addAttribute("logss", logsRepository.findByAction(searchstring1));
        }

        return "/admin";
    }

    @PostMapping("/search")
    public String search1(@RequestParam("searchstr") String searchstr) {
        searchstring = searchstr;
        return "redirect:/admin/admin";
    }

    @PostMapping("/searchlo")
    public String search(@RequestParam("searchlog") String searchlog) {
        searchstring1 = searchlog;
        return "redirect:/admin/admin";
    }


    @Autowired
    private com.example.cruddemo.repositories.userRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String userView(Model model)
    {
        model.addAttribute("user_list", userRepository.findAll());
        return "admin";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model)
    {
        model.addAttribute("user_object",userRepository.findById(id).orElseThrow());
        return "info";
    }

    @GetMapping("/{id}/update")
    public String updView(@PathVariable Long id, Model model)
    {
        model.addAttribute("user_object",userRepository.findById(id).orElseThrow());
        model.addAttribute("roles", roleEnum.values());
        return "update";
    }


    @PostMapping("/{id}/update")
    public String update_user(@RequestParam String username, @RequestParam String password,
                              @RequestParam(name="roles[]", required = false) String[] roles,
                              @PathVariable Long id)
    {
        modelUser user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        user.getRoles().clear();
        if(roles != null)
        {
            for(String role: roles)
            {
                user.getRoles().add(roleEnum.valueOf(role));
            }
        }

        userRepository.save(user);
        return "redirect:/admin/{id}";
    }
}
