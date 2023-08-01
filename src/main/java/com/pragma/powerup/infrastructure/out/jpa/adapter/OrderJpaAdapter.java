package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.OrderState;
import com.pragma.powerup.domain.spi.IOrderPersistencePort;
import com.pragma.powerup.infrastructure.exception.OrderNotFoundException;
import com.pragma.powerup.infrastructure.exception.UserCannotMakeAnOrderException;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {

    private final IOrderRepository orderRepository;
    private final IOrderEntityMapper orderEntityMapper;

    @Override
    public OrderModel createOrder(OrderModel orderModel) {
        return orderEntityMapper.toOrderModel(orderRepository.save(orderEntityMapper.toOrderEntity(orderModel)));
    }

    @Override
    public OrderModel getOrder(Long orderId) {
        return orderEntityMapper.toOrderModel(orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new));
    }

    @Override
    public List<OrderModel> getAllOrdersByOrderState(OrderState orderState, Long restaurantId) {
        return orderEntityMapper.toOrderModelList(orderRepository.findAllByOrderState(orderState, restaurantId));
    }

    @Override
    public Boolean getAllOrdersByUserIdOrderStateIn(Long clientId, List<OrderState> orderStateList) {
        Optional<OrderEntity> orderEntityOptional = orderRepository.findByClientIdAndOrderStateIn(clientId, orderStateList);

        if (!orderEntityOptional.isEmpty()) {
            throw new UserCannotMakeAnOrderException();
        }

        return true;
    }
}
