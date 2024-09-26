package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.response.ResponseHandler;
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
    public ResponseEntity<Object> getUserProfile(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        Customer customer = customerService.getCustomerProfile(jwtToken);
        return ResponseHandler.responseBuilder("Success", HttpStatus.OK, customer);
    }

    @PutMapping("/manage-profile/change-password")
    public ResponseEntity<Object> changePassword(HttpServletRequest request){
        String username = request.getParameter("username");
        String newpassword = request.getParameter("newpassword");

        try {
            Customer cus = customerService.customerChangePassword(username, newpassword);
            // response
            return ResponseHandler.responseBuilder("Change password successfully", HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseHandler.responseBuilder(e.getMessage(),HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/manage-profile/edit-profile")
    public ResponseEntity<Object> editProfile(HttpServletRequest request){
        String username = request.getParameter("username");
        String newPhonenumber = request.getParameter("newPhonenumber");
        String newEmail = request.getParameter("newEmail");

        try {
            Customer cus = customerService.customerEditProfile(username, newPhonenumber, newEmail);
            return  ResponseHandler.responseBuilder("successfully", HttpStatus.OK);
        }catch (RuntimeException e){
            return ResponseHandler.responseBuilder(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }


}
