package com.codingbytime.zexfora.boundary;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingbytime.zexfora.control.exception.CustomResponse;
import com.codingbytime.zexfora.control.service.WebsiteService;
import com.codingbytime.zexfora.entity.dto.UserRegisterDTO;
import com.codingbytime.zexfora.entity.table.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/website")
public class WebsiteController {

    private final WebsiteService websiteService;

    @PostMapping("/add")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<CustomResponse<Boolean>> addWebsite(@Valid @RequestBody String url) {
        return new CustomResponse<Boolean>().responseCreated((websiteService.addWebsite(url)));
    }
}
