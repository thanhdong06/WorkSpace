package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.Customer;
import fpt.swp.WorkSpace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer user = userRepository.findByuserName(username);
        if (user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
