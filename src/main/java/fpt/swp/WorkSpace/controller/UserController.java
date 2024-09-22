package fpt.swp.WorkSpace.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class UserController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello User";
    }
}
