package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.repository.CustomerRepository;
import fpt.swp.WorkSpace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer getCustomerProfile(String token) {
        String username = jwtService.extractUsername(token);
        Customer customer =  customerRepository.findCustomerByUsername(username);
        return customer;
    }
}
