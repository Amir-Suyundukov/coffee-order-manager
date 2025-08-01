package ru.suyu.coffee_order_manager.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.suyu.coffee_order_manager.domain.Role;
import ru.suyu.coffee_order_manager.domain.User;
import ru.suyu.coffee_order_manager.repository.RoleRepository;
import ru.suyu.coffee_order_manager.repository.UserRepository;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        log.info("Начинаем инициализацию начальных данных ...");
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN");
        Role waiterRole = createRoleIfNotFound("ROLE_WAITER");
        Role customerRole = createRoleIfNotFound("ROLE_CUSTOMER");

        if (userRepository.findByUsername("admin").isEmpty()){
            log.info("Создаем пользователя 'admin'");

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("AdminPass"));// по факту пароль должен быть сложным
            admin.setEmail("admin@coffeshop.com");
            admin.setFirstName("Главный");
            admin.setLastName("Администратор");
            admin.setEnabled(true);
            admin.setRoles(Set.of(adminRole));

            userRepository.save(admin);
            log.info("'admin' создан успешно");
        }
        log.info("инициализация данных завершена");
    }

    private Role createRoleIfNotFound(String name){
        return roleRepository.findByName(name)
                .orElseGet(() -> {
                    log.info("Создаем роль: {}", name);
                    return roleRepository.save(new Role(null, name , null, null));
                });
    }
}
