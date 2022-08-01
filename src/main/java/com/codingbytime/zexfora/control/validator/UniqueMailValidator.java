package com.codingbytime.zexfora.control.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.codingbytime.zexfora.control.repository.UserRepository;
import com.codingbytime.zexfora.control.validator.annotations.UniqueMail;
import com.codingbytime.zexfora.entity.table.User;

public class UniqueMailValidator implements ConstraintValidator<UniqueMail, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {
        if (mail != null) {
            Optional<User> byUsername = userRepository.findByMail(mail);
            return byUsername.isEmpty();
        }

        return true;
    }
}
