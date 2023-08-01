package com.pragma.powerup.application.handler;


import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.OrderStateResponseDto;
import com.pragma.powerup.domain.model.OrderState;

import java.util.List;

public interface IOrderHandler {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto);

    OrderResponseDto getOrder(Long orderId);

    List<OrderStateResponseDto> getAllOrdersByOrderState(OrderState orderState);

    OrderResponseDto asignAnOrder(Long orderId);

    OrderResponseDto notifyOrder(Long orderId);

    OrderResponseDto deliverOrder(Long orderId, Long pin);

    OrderResponseDto cancelOrder(Long orderId);

}
