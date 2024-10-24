package upload_firebase.demo_security_not_register.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import upload_firebase.demo_security_not_register.model.entity.Role;
import upload_firebase.demo_security_not_register.model.entity.User;
import upload_firebase.demo_security_not_register.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUsername(username);
        if(user!=null){
            return CustomUserDetails.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .fullName(user.getFullName())
                    .gender(user.getGender())
                    .birthday(user.getBirthday())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .enable(user.getEnabled())
                    .authorities(mapToGrandAuthorties(user.getRoles()))
                    .build();
        }
        return null;
    }

    private List<GrantedAuthority> mapToGrandAuthorties(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}
