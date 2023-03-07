package com.maersk.erp.mappers;

import com.maersk.erp.model.Bookings;
import com.maersk.erp.wrapper.BookingRequest;
import org.modelmapper.ModelMapper;

public class BookingsMapper {

    public static Bookings map(BookingRequest reversalLogModel) {

        ModelMapper mapper = new ModelMapper();
        return mapper.map(reversalLogModel, Bookings.class);
    }

}
