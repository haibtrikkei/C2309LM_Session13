package upload_firebase.demo_security_not_register.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import upload_firebase.demo_security_not_register.sercurity.CustomUserDetails;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping
    public String homeUser(Model model){
        CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user",user);
        return "user";
    }
}
