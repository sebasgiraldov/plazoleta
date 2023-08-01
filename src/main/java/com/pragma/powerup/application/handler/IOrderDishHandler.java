package com.pragma.powerup.application.handler;
import com.pragma.powerup.application.dto.request.OrderDishRequestDto;
import com.pragma.powerup.application.dto.response.OrderDishResponseDto;

import java.util.List;

public interface IOrderDishHandler {
    OrderDishResponseDto createOrderDish(OrderDishRequestDto orderDishRequestDto, Long orderId);

    OrderDishResponseDto getOrderDish(Long orderDishId);

    List<OrderDishResponseDto> getAllOrderDishByOrder(Long orderId);
}
