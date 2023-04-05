package ru.kata.spring.boot_security.demo;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class Initializer {
    private final UserService userService;


    public Initializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        User user = userService.getUserByEmail("admin@mail.ru");
        if (user == null) {
            Role userRole = new Role("ROLE_USER");
            Role adminRole = new Role("ROLE_ADMIN");
            List<Role> adminRoles = new ArrayList<>();
            adminRoles.add(userRole);
            adminRoles.add(adminRole);
            User admin = new User(adminRoles, "admin", "admin", (byte) 40, "admin@mail.ru", "admin@mail.ru", "admin");

            userService.save(admin);
        }
    }
}