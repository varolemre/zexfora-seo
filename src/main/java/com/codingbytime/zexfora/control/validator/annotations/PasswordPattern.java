package com.codingbytime.zexfora.control.validator.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.codingbytime.zexfora.control.validator.PasswordPatternValidator;

@Documented
@Constraint(validatedBy = PasswordPatternValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(PasswordPattern.List.class)
public @interface PasswordPattern {

    /**
     * @return the error message template
     */
    String message() default "Password is invalid";

    /**
     * @return the groups the constraint belongs to
     */
    Class<?>[] groups() default {};

    /**
     * @return the payload associated to the constraint.
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link PasswordPattern} constraints on the same element.
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        PasswordPattern[] value();
    }
}
