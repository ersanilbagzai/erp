package com.maersk.erp.wrapper;

import com.maersk.erp.annotation.BookingRequestValidator;
import com.maersk.erp.enums.ContainerType;
import lombok.Data;

@Data
@BookingRequestValidator
public class BookingRequest {

    private Integer containerSize;
    private ContainerType containerType;
    private String origin;
    private String destination;
    private Integer quantity;
    private String timestamp;

}
