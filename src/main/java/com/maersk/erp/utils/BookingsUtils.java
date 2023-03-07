package com.maersk.erp.utils;

public class BookingsUtils {

    public static Integer getBookingRef(){
        Double id = Math.random();
        return id.intValue();
    }
}
