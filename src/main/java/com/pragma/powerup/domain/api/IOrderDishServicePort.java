package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderDishModel;

import java.util.List;

public interface IOrderDishServicePort {
    OrderDishModel createOrderDish(OrderDishModel orderDishModel);

    OrderDishModel getOrderDish(Long orderDishId);

    List<OrderDishModel> getAllOrderDish();

    List<OrderDishModel> getAllOrderDishByOrder(Long orderId);
}
