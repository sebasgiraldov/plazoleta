package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.OrderState;

import java.util.List;
import java.util.Optional;

public interface IOrderPersistencePort {
    OrderModel createOrder(OrderModel orderModel);

    OrderModel getOrder(Long orderId);

    List<OrderModel> getAllOrdersByOrderState(OrderState orderState, Long restaurantId);

    Boolean getAllOrdersByUserIdOrderStateIn(Long clientId, List<OrderState> orderStateList);
}
