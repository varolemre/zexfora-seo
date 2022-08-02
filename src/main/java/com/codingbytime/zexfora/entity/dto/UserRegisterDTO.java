package com.codingbytime.zexfora.entity.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.codingbytime.zexfora.control.validator.annotations.PasswordPattern;
import com.codingbytime.zexfora.control.validator.annotations.UniqueMail;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRegisterDTO {
    @Email(message = "{user.registration.mail.error}")
    @NotBlank(message = "{user.registration.mail.blank.error}")
    @UniqueMail(message = "1004")
    private String mail;

    @NotBlank(message = "{user.registration.firstname.error}")
    private String firstName;

    @NotEmpty(message = "{user.registration.password.error}")
    @Size(min = 6, message = "{user.registration.password.size}")
    @PasswordPattern(message = "{user.registration.password.pattern}")
    private char[] password;

}

