package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.OrderState;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {

    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        return orderPersistencePort.createOrder(orderModel);
    }

    @Override
    public OrderModel getOrder(Long orderId) {
        return orderPersistencePort.getOrder(orderId);
    }

    @Override
    public List<OrderModel> getAllOrdersByOrderState(OrderState orderState, Long restaurantId) {
        return orderPersistencePort.getAllOrdersByOrderState(orderState, restaurantId);
    }

    @Override
    public Boolean getAllOrdersByUserIdOrderStateIn(Long clientId, List<OrderState> orderStateList) {
        return orderPersistencePort.getAllOrdersByUserIdOrderStateIn(clientId, orderStateList);
    }
}
