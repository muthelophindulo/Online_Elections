package com.Eelections.Configuration;

import com.Eelections.User.User;
import com.Eelections.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/login",
                                "/register",
                                "/javascript",
                                "/css"
                                ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successForwardUrl("/dashboard")
                        .failureUrl("/login?error=true")
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/login?logout=true")
                        .logoutUrl("/logout")
                        .permitAll()
                )
                .csrf(csfr -> csfr.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
       return username -> {
            //get the user from the database
            User user = userService.findByIdNo(username);

            //check if the user is null or not
           if(user == null){
               throw new UsernameNotFoundException("user was not found");
           }

           return org.springframework.security.core.userdetails.User
                   .withUsername(user.getIdNo())
                   .password(user.getPassword())
                   .roles(user.getRole())
                   .build();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
