package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.service.ICustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello User";
    }

    @GetMapping("/manage-profile")
    public ResponseEntity<Customer> getUserProfile(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        Customer customer = customerService.getCustomerProfile(jwtToken);
        return  ResponseEntity.ok(customer);
    }

    @PutMapping("/manage-profile/change-password")
    public ResponseEntity<Customer> changePassword(HttpServletRequest request){
        String username = request.getParameter("username");
        String newpassword = request.getParameter("newpassword");
        Customer cus = customerService.customerChangePassword(username, newpassword);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cus);
    }


}
