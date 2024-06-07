package com.examination.system.admin.controller;

import com.examination.system.admin.entity.AdminDetails;
import com.examination.system.admin.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminlogin")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class AdminLoginController {
    @Autowired
    ServiceInterface serviceInterface;
    @PostMapping("/add")
    public AdminDetails addNewAdmin(@RequestBody AdminDetails adminDetails){
       return serviceInterface.addAdmin(adminDetails);
    }
    @GetMapping("/getall")
    public java.util.List<AdminDetails> getAllAdmins(){
        return serviceInterface.getAllAdmin();
    }
    //AdminLogin
    @PostMapping("/login")
    public ResponseEntity<?>adminLogin(@RequestParam String adminEmail,@RequestParam String adminPassword){
      return serviceInterface.adminLogin(adminEmail,adminPassword);
    }

}
