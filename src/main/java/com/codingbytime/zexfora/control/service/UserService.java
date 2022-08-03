package com.codingbytime.zexfora.control.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.codingbytime.zexfora.control.repository.RoleRepository;
import com.codingbytime.zexfora.control.repository.UserRepository;
import com.codingbytime.zexfora.entity.dto.UserRegisterDTO;
import com.codingbytime.zexfora.entity.enums.UserRoleEnum;
import com.codingbytime.zexfora.entity.table.User;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    @SneakyThrows
    @Transactional(propagation = Propagation.MANDATORY)
    public User createUser(UserRegisterDTO userRegisterDTO) {
        User user = modelMapper.map(userRegisterDTO, User.class);
        user.setPassword(passwordEncoder.encode(String.valueOf(userRegisterDTO.getPassword())));
        user.setRoles(new HashSet<>(List.of(roleRepository.findByName(UserRoleEnum.ROLE_USER.name()))));
        user.setName(userRegisterDTO.getFirstName());
        user.setActive(true);
        user.setMembership("ST");
        User savedUser = userRepository.save(user);
        emailService.sendWelcomeLink(savedUser, LocaleContextHolder.getLocale());
        return savedUser;
    }
}
