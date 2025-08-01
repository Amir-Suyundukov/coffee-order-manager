package ru.suyu.coffee_order_manager.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.suyu.coffee_order_manager.domain.Role;
import ru.suyu.coffee_order_manager.domain.User;
import ru.suyu.coffee_order_manager.repository.RoleRepository;
import ru.suyu.coffee_order_manager.repository.UserRepository;
import ru.suyu.coffee_order_manager.service.UserService;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User registerNewUser(String username, String password, String email) {
        if(userRepository.findByUsername(username).isPresent()){
            throw new RuntimeException("User with username + " + username + " already exists");
        }

        Role castomerRole = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setEmail(email);
        newUser.setRoles(new HashSet<>(Collections.singletonList(castomerRole)));
        newUser.setEnabled(true);

        return userRepository.save(newUser);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
