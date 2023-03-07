package com.maersk.erp.annotation;



import com.maersk.erp.validators.BookingRequestAttributeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BookingRequestAttributeValidator.class)
public @interface BookingRequestValidator {
    public String message() default "filed must not empty";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}

