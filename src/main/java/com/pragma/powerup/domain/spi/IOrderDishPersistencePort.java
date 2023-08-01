package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderDishModel;

import java.util.List;

public interface IOrderDishPersistencePort {
    OrderDishModel createOrderDish(OrderDishModel orderDishModel);

    OrderDishModel getOrderDish(Long orderDishId);
    List<OrderDishModel> getAllOrderDish();

    List<OrderDishModel> getAllOrderDishByOrder(Long orderId);
}
