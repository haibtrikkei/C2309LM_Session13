package upload_firebase.demo_security_not_register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import upload_firebase.demo_security_not_register.model.entity.User;
import upload_firebase.demo_security_not_register.repository.UserRepository;
import upload_firebase.demo_security_not_register.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
