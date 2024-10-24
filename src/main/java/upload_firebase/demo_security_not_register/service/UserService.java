package upload_firebase.demo_security_not_register.service;

import upload_firebase.demo_security_not_register.model.entity.User;

public interface UserService {
    User findUserByUsername(String username);
}
