package fpt.swp.WorkSpace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staff")
public class StaffController {

    @GetMapping("/hello-staff")
    public String helloStaff() {
        return "Hello Staff";
    }
}
