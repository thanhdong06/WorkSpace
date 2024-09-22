package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/hello")
    public String hello() {
        return "Hello User";
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization") String token){
        String jwtToken = token.substring(7);
        User user = userService.getUserProfile(jwtToken);
        return  ResponseEntity.ok(user);
    }
}
