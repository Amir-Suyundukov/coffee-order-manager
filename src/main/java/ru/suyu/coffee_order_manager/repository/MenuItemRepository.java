package ru.suyu.coffee_order_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.suyu.coffee_order_manager.domain.MenuItem;

import java.util.Optional;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    Optional<MenuItem> findByCategory_Name(String categoryName);
}
