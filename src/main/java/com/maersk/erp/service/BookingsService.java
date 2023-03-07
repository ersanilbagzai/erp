package com.maersk.erp.service;

import com.maersk.erp.enums.ContainerType;
import com.maersk.erp.mappers.BookingsMapper;
import com.maersk.erp.model.Bookings;
import com.maersk.erp.repository.BookingsRepository;
import com.maersk.erp.utils.BookingsUtils;
import com.maersk.erp.utils.RestTemplateHelper;
import com.maersk.erp.wrapper.BookContainerResponse;
import com.maersk.erp.wrapper.BookingRequest;
import com.maersk.erp.wrapper.CheckAvailableResponse;
import com.maersk.erp.wrapper.ExternalResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BookingsService {

    private static final Logger log = LogManager.getLogger(BookingsService.class);

    @Autowired
    BookingsRepository bookingsRepository;

    @Autowired
    RestTemplateHelper restTemplateHelper;

    public CheckAvailableResponse checkAvailable(BookingRequest request) throws IOException {

        ExternalResponse response = restTemplateHelper.postForEntity("http://localhost:9293/api/bookings/check/containers",
                request, new HttpHeaders(), ExternalResponse.class);
        return CheckAvailableResponse.builder().available(response.getAvailableSpace() > 0).build();
    }

    public BookContainerResponse bookContainer(BookingRequest request) {
        Integer bookingRef = BookingsUtils.getBookingRef();

        Bookings booking = BookingsMapper.map(request);
        booking.setBookingRef(bookingRef);

        Bookings savedBooking = bookingsRepository.save(booking);
        return BookContainerResponse.builder().bookingRef(savedBooking.getBookingRef().toString()).build();
    }
}
