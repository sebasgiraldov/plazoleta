package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.OrderState;

import java.util.List;

public interface IOrderServicePort {
    OrderModel createOrder(OrderModel orderModel);

    OrderModel getOrder(Long orderId);

    List<OrderModel> getAllOrdersByOrderState(OrderState orderState, Long restaurantId);
    Boolean getAllOrdersByUserIdOrderStateIn(Long clientId, List<OrderState> orderStateList);
}
