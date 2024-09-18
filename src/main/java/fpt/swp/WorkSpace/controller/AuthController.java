package fpt.swp.WorkSpace.controller;

import fpt.swp.WorkSpace.auth.AuthenticationResponse;
import fpt.swp.WorkSpace.auth.LoginRequest;
import fpt.swp.WorkSpace.auth.RegisterRequest;

import fpt.swp.WorkSpace.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private IAuthService service;

    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){

        return ResponseEntity.ok(service.register(request)) ;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest request){

        return ResponseEntity.ok(service.login(request)) ;
    }





}
