package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.auth.AuthenticationResponse;
import fpt.swp.WorkSpace.auth.LoginRequest;
import fpt.swp.WorkSpace.auth.RegisterRequest;

import fpt.swp.WorkSpace.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private IAuthService service;

    @PostMapping("/auth/register")
    public AuthenticationResponse register(@Valid @RequestBody RegisterRequest request){
        AuthenticationResponse response = service.register(request);
        return response;
    }

    @PostMapping("/auth/login")
    public AuthenticationResponse login(@Valid @RequestBody LoginRequest request){

         AuthenticationResponse response = service.login(request);
        return response;
    }



//    @GetMapping("/auth/test")
//    public AuthenticationResponse test(@RequestBody RegisterRequest request){
//        AuthenticationResponse response = service.register(request);
//        return response;
//    }







}
