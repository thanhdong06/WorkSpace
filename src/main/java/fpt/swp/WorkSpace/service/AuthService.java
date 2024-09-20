package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.auth.AuthenticationResponse;
import fpt.swp.WorkSpace.auth.LoginRequest;
import fpt.swp.WorkSpace.auth.RegisterRequest;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashMap;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;



    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();

        try {
            if (!request.getPassword().equals(request.getPasswordConfirm())){
                throw new IllegalAccessException("Passwords do not match");
            }
            Customer newUser = new Customer();
            newUser.setUserName(request.getUserName());

            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setFullName(request.getFullName());
            newUser.setCreatedDate(new Date(System.currentTimeMillis()));
            newUser.setDateOfBirth(request.getDateOfBirth());
            newUser.setPhoneNumber(request.getPhoneNumber());
            newUser.setRoleName(request.getRole());
            Customer result = repository.save(newUser);
            if (result.getUserId() > 0){
                response.setRefreshToken(jwtService.generateAccessToken(new HashMap<>(), request.getUserName()));
                response.setAccesstoken(jwtService.generateRefreshToken(request.getUserName()));
                response.setStatusCode(200);
                response.setMessage("User Saved Successfully");
                response.setRole(request.getRole());
            }
        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        System.out.println(response);
        return response;
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            Customer user = repository.findByuserName(request.getUserName());
            if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())){
                throw new IllegalAccessException("User not found or Password do not match");
            }
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            String jwt = jwtService.generateAccessToken(new HashMap<>(),user.getUsername());
            String refreshToken = jwtService.generateRefreshToken(user.getUsername());
            response.setStatusCode(200);
            response.setAccesstoken(jwt);
            response.setRefreshToken(refreshToken);
            response.setMessage("Successfully Logged In");
            response.setRole(user.getRoleName());
        }catch (IllegalAccessException e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @Override
    public AuthenticationResponse refresh(HttpServletRequest request) {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();

            // get header
            final String authHeader = request.getHeader("Authorization");
            final String refreshToken;     // our token
            final String userName;

            // check JWT Token
            if (authHeader != null || authHeader.startsWith("Bearer ")){
                refreshToken = authHeader.substring(7);
                //extract the username from JWT token
                userName = jwtService.extractUsername(refreshToken);
                // Check validtation of token
                if (userName != null){
                    UserDetails userDetails = repository.findByuserName(userName);
                    if (jwtService.isTokenValid(refreshToken,userDetails)){
                        String accessToken = jwtService.generateAccessToken(new HashMap<>(), userDetails.getUsername());
                        authenticationResponse.setStatusCode(200);
                        authenticationResponse.setAccesstoken(accessToken);
                        authenticationResponse.setRefreshToken(refreshToken);
                    }
                }
            }
            return authenticationResponse;
        }




}