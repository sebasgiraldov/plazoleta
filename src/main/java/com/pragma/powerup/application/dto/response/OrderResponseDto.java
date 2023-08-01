package com.pragma.powerup.application.dto.response;

import com.pragma.powerup.domain.model.OrderState;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long orderId;
    private Date date;
    private OrderState orderState;
    private RestaurantResponseDto restaurantId;
    private List<OrderDishResponseDto> orders;
}
