package com.maersk.erp.model;

import com.maersk.erp.enums.ContainerType;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Bookings {


    @PrimaryKey
    private Integer bookingRef;
    private Integer containerSize;
    private ContainerType containerType;
    private String origin;
    private String destination;
    private Integer quantity;
    private String timestamp;

}
