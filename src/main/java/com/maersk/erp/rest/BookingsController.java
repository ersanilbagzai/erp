package com.maersk.erp.rest;

import com.maersk.erp.exception.ParameterNotValidException;
import com.maersk.erp.service.BookingsService;
import com.maersk.erp.wrapper.BookContainerResponse;
import com.maersk.erp.wrapper.BookingRequest;
import com.maersk.erp.wrapper.CheckAvailableResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/bookings")
@Validated
public class BookingsController {

    private static final Logger log = LogManager.getLogger(BookingsController.class);

    @Autowired
    private BookingsService bookingsService;

    @PostMapping(value = "checkAvailable", produces = "application/json")
    public CheckAvailableResponse checkAvailable(@Valid @RequestBody BookingRequest reversalRequest) throws IOException, ParameterNotValidException {
        return bookingsService.checkAvailable(reversalRequest);
    }

    @PostMapping(value = "book/container", produces = "application/json")
    public BookContainerResponse bookContainer(@RequestBody BookingRequest reversalRequest) {
        return bookingsService.bookContainer(reversalRequest);
    }
}
