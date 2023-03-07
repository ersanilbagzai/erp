package com.maersk.erp.validators;

import com.maersk.erp.annotation.BookingRequestValidator;
import com.maersk.erp.exception.ParameterNotValidException;
import com.maersk.erp.wrapper.BookingRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BookingRequestAttributeValidator implements ConstraintValidator<BookingRequestValidator, BookingRequest> {

    private static final Logger log = LogManager.getLogger(BookingRequestAttributeValidator.class);

    public boolean isValid(BookingRequest bookingRequest, ConstraintValidatorContext cxt) {

        if (bookingRequest.getContainerSize() == null) {
            log.error("ParameterNotValidException in BookingRequest. Code : {}, Message : {}", "ER102", "containerSize is required");
            throw new ParameterNotValidException("ER102",
                    "containerSize is required", HttpStatus.OK.value(), HttpStatus.OK);
        }
        if (!(bookingRequest.getContainerSize() == 20 || bookingRequest.getContainerSize() == 40)) {
            throw new ParameterNotValidException("ER103",
                    "containerSize can only be 20 or 40", HttpStatus.OK.value(), HttpStatus.OK);
        }
        if (bookingRequest.getContainerType() == null) {
            throw new ParameterNotValidException("ER104",
                    "containerType is required", HttpStatus.OK.value(), HttpStatus.OK);
        }
        if (bookingRequest.getOrigin() == null) {
            throw new ParameterNotValidException("ER105",
                    "origin is required", HttpStatus.OK.value(), HttpStatus.OK);
        }
        if (bookingRequest.getDestination() == null) {
            throw new ParameterNotValidException("ER106",
                    "destination is required", HttpStatus.OK.value(), HttpStatus.OK);
        }
        if (bookingRequest.getQuantity() == null) {
            throw new ParameterNotValidException("ER107",
                    "quantity is required", HttpStatus.OK.value(), HttpStatus.OK);
        }
        if (!(bookingRequest.getQuantity() >= 1 && bookingRequest.getQuantity() <= 100)) {
            throw new ParameterNotValidException("ER108",
                    "quantity can only be between 1 - 100", HttpStatus.OK.value(), HttpStatus.OK);
        }
        return true;
    }
}
