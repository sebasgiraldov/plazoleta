package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantEmployeeResponseDto {
    private Long restaurantId;
    private Long employeeId;
    private String field;
}
