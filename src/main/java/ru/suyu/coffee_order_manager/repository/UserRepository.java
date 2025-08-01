package ru.suyu.coffee_order_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suyu.coffee_order_manager.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User ,Long> {

    Optional<User> findByUsername(String username);
}
