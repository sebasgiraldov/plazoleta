package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderDishServicePort;
import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;

import java.util.List;

public class OrderDishUseCase implements IOrderDishServicePort {

    private final IOrderDishPersistencePort orderDishPersistencePort;

    public OrderDishUseCase(IOrderDishPersistencePort orderDishPersistencePort) {
        this.orderDishPersistencePort = orderDishPersistencePort;
    }

    @Override
    public OrderDishModel createOrderDish(OrderDishModel orderDishModel) {
        return orderDishPersistencePort.createOrderDish(orderDishModel);
    }

    @Override
    public OrderDishModel getOrderDish(Long orderDishId) {
        return orderDishPersistencePort.getOrderDish(orderDishId);
    }

    @Override
    public List<OrderDishModel> getAllOrderDish() {
        return orderDishPersistencePort.getAllOrderDish();
    }

    @Override
    public List<OrderDishModel> getAllOrderDishByOrder(Long orderId) {
        return orderDishPersistencePort.getAllOrderDishByOrder(orderId);
    }
}
