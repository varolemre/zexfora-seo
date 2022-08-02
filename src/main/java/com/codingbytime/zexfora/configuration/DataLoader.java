package com.codingbytime.zexfora.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import com.codingbytime.zexfora.control.repository.RoleRepository;
import com.codingbytime.zexfora.control.repository.UserRepository;
import com.codingbytime.zexfora.entity.enums.UserRoleEnum;
import com.codingbytime.zexfora.entity.table.Role;
import com.codingbytime.zexfora.entity.table.User;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final Environment environment;

    @Override
    public void run(String... args) {
        createRoles();
        if (Arrays.stream(environment.getActiveProfiles()).collect(Collectors.toList()).contains("local")) {
            createAdmin("admin@shell.com", "123456");
        } else if (Arrays.stream(environment.getActiveProfiles()).collect(Collectors.toList()).contains("prod")) {
            createAdmin("admin@bedavacadde.com", "ZVqTSTjcq6");
        }

    }

    private void createRoles() {
        for (UserRoleEnum role : UserRoleEnum.values()) {
            Role myRole = new Role();
            myRole.setName(role.name());
            if (!roleRepository.existsByName(role.name())) {
                Role save = roleRepository.save(myRole);
                log.info("Created role: " + save.getName());
            }
        }
    }

    private void createAdmin(String mail, String pass) {
        Optional<User> byMail = userRepository.findByMail(mail);
        if (byMail.isEmpty()) {
            User user = new User();
            user.setMail(mail);
            user.setName("zexfora");
            user.setPassword(new BCryptPasswordEncoder().encode(pass));
            HashSet<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(UserRoleEnum.ROLE_ADMIN.name()));
            user.setRoles(roles);
            User save = userRepository.save(user);
            log.info("Created user: " + save.getMail());
        }
    }
}

