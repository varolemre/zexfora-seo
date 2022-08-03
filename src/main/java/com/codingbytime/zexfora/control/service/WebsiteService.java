package com.codingbytime.zexfora.control.service;

import org.springframework.stereotype.Service;

import com.codingbytime.zexfora.control.repository.WebsiteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebsiteService {

    private final WebsiteRepository websiteRepository;

    public boolean addWebsite(String url) {

        return true;
    }
}
