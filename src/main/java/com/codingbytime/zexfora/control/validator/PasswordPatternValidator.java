package com.codingbytime.zexfora.control.validator;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.lang3.ArrayUtils;

import com.codingbytime.zexfora.control.validator.annotations.PasswordPattern;

public class PasswordPatternValidator implements ConstraintValidator<PasswordPattern, char[]> {

    private Pattern pattern;

    @Override
    public void initialize(PasswordPattern constraintAnnotation) {

        try {
            this.pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-=<>:;~]).{8,}$");
        } catch (PatternSyntaxException e) {
            throw new IllegalArgumentException("Given regex is invalid", e);
        }
    }

    @Override
    public boolean isValid(char[] value, ConstraintValidatorContext context) {

        if (ArrayUtils.isEmpty(value)) {
            return true;
        }

        return pattern.matcher(ArrayUtils.toString(value)).matches();
    }
}
