package ru.suyu.coffee_order_manager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.suyu.coffee_order_manager.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean//шифр
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean//аутентификация(или аутизм) бин
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean//собственный провайдер аутентификации
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService(userService));//ищем
        authProvider.setPasswordEncoder(passwordEncoder());//шифруем
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())//на время отключаем csrf
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").hasRole("ADMIN")//только админы
                        .requestMatchers("/orders/**").hasAnyRole("ADMIN","WAITER")//админы и официанты
                        .anyRequest().permitAll())
                .formLogin(form -> form.permitAll());

        return http.build();
    }

    public UserDetailsService userDetailsService(UserService userService) {
        return username -> (org.springframework.security.core.userdetails.UserDetails) userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username '" + username + "' not found"));
    }

}
