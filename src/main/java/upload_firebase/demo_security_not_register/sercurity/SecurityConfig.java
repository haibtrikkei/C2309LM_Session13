package upload_firebase.demo_security_not_register.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(httpRequest->httpRequest.requestMatchers("/login").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
//                .requestMatchers("/accounting/**").hasAnyRole("ADMIN","ACCOUNTING")
                .requestMatchers("/moderator/**").hasAnyRole("ADMIN","MODERATOR")
                        .requestMatchers("/static/**").permitAll()
                        .anyRequest().permitAll())
                .formLogin(form->
                        form.loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/user")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .permitAll())
                .logout(logout->logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll());


        return httpSecurity.build();
    }




    @Autowired
    public void configGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
