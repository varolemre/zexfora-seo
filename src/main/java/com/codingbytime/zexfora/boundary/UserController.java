package com.codingbytime.zexfora.boundary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingbytime.zexfora.control.exception.CustomResponse;
import com.codingbytime.zexfora.control.repository.UserRepository;
import com.codingbytime.zexfora.control.service.UserService;
import com.codingbytime.zexfora.entity.dto.UserRegisterDTO;
import com.codingbytime.zexfora.entity.table.User;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<CustomResponse<Boolean>> createUser(@Valid @RequestBody UserRegisterDTO registerDTO) {
        User user = userService.createUser(registerDTO);
        return new CustomResponse<Boolean>().responseCreated((user.isActive()));
    }
}
