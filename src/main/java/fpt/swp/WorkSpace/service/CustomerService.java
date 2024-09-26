package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.repository.CustomerRepository;
import fpt.swp.WorkSpace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

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
            if (passwordEncoder.matches(newpassword, customer.getUser().getPassword() )){
                throw new RuntimeException("Old password");
            }
            if (newpassword == null){
                throw new RuntimeException("Not empty");
            }
            customer.getUser().setPassword(passwordEncoder.encode(newpassword));
            customerRepository.save(customer);
        }

        return customer;
    }

    @Override
    public Customer customerEditProfile(String username, String newPhonenumber, String newEmail) {
        Customer customer =  customerRepository.findCustomerByUsername(username);

            if (customer != null) {
                if (newPhonenumber == null || newPhonenumber.length() != 10) {
                    throw new RuntimeException("PhoneNumber should be 10 digits");
                }
                if (newEmail == null || Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(newEmail).matches()) {
                    throw new RuntimeException("Email is not in valid format");
                }
                customer.setPhoneNumber(newPhonenumber);
                customer.setEmail(newEmail);
                customerRepository.save(customer);
            }

        return customer;
    }
}
