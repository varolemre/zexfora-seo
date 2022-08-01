package com.codingbytime.zexfora.control.service;

import org.springframework.stereotype.Service;

import com.codingbytime.zexfora.control.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
}
