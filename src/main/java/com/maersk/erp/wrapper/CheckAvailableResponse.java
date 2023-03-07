package com.maersk.erp.wrapper;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CheckAvailableResponse {

    private Boolean available;
}
