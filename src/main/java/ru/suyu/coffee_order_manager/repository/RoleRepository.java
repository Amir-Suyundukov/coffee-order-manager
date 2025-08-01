package ru.suyu.coffee_order_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suyu.coffee_order_manager.domain.Role;
import ru.suyu.coffee_order_manager.domain.User;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role ,Long> {

    Optional<Role> findByName(String name);
}
