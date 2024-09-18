package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.auth.AuthenticationResponse;
import fpt.swp.WorkSpace.auth.LoginRequest;
import fpt.swp.WorkSpace.auth.RegisterRequest;
import fpt.swp.WorkSpace.models.AppUser;
import fpt.swp.WorkSpace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        AuthenticationResponse response = new AuthenticationResponse();
        try {
            AppUser newUser = new AppUser();
            newUser.setUserName(request.getUserName());
            newUser.setPassword(passwordEncoder.encode(request.getPassword()));
            newUser.setFullName(request.getFullName());
            newUser.setCreatedDate(new Date(System.currentTimeMillis()));
            newUser.setDateOfBirth(request.getDateOfBirth());
            newUser.setPhoneNumber(request.getPhoneNumber());
            newUser.setRoleName(request.getRole());
            AppUser result = repository.save(newUser);
            String jwt = jwtService.generateToken(newUser);
            if (result.getUserId() > 0){
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setMessage("User Saved Successfully");
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
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
            UserDetails user = repository.findByuserName(request.getUserName()).orElseThrow();
            String jwt = jwtService.generateToken(user);

            response.setStatusCode(200);
            response.setToken(jwt);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        }catch (Exception e){
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return null;
    }
}
