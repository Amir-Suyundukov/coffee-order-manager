package ru.suyu.coffee_order_manager.service;

import ru.suyu.coffee_order_manager.domain.User;

import java.util.Optional;

public interface UserService {

    User registerNewUser(String username, String password, String email);

    Optional<User> findByUsername(String username);
}
