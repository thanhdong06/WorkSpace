package fpt.swp.WorkSpace.service;

import fpt.swp.WorkSpace.models.User;
import fpt.swp.WorkSpace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(String token) {
        String username = jwtService.extractUsername(token);
        User user = userRepository.findByuserName(username);

        return user;
    }
}
