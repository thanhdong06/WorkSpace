package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.repository.CustomerRepository;
import fpt.swp.WorkSpace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Customer getCustomerProfile(String token) {
        String username = jwtService.extractUsername(token);
        Customer customer =  customerRepository.findCustomerByUsername(username);
        return customer;
    }

    @Override
    public Customer customerChangePassword(String username, String newpassword) {
        Customer customer =  customerRepository.findCustomerByUsername(username);
        if (customer != null) {
            if (!passwordEncoder.matches(newpassword, customer.getUser().getPassword() )){
                customer.getUser().setPassword(passwordEncoder.encode(newpassword));
                customerRepository.save(customer);
            }
        }
        return customer;
    }

    @Override
    public Customer customerEditProfile(String token, Customer customer) {

        return null;
    }
}
