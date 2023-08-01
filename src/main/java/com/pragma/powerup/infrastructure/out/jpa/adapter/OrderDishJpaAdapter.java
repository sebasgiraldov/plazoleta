package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.domain.spi.IOrderDishPersistencePort;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderDishEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderDishRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class OrderDishJpaAdapter implements IOrderDishPersistencePort {

    private final IOrderDishRepository orderDishRepository;
    private final IOrderDishEntityMapper orderDishEntityMapper;

    @Override
    public OrderDishModel createOrderDish(OrderDishModel orderDishModel) {
        return orderDishEntityMapper.toOrderDishModel(orderDishRepository.save(orderDishEntityMapper.toOrderDishEntity(orderDishModel)));
    }

    @Override
    public OrderDishModel getOrderDish(Long orderDishId) {
        return null;
    }

    @Override
    public List<OrderDishModel> getAllOrderDish() {
        return orderDishEntityMapper.toOrderDishModelList(orderDishRepository.findAll());
    }

    @Override
    public List<OrderDishModel> getAllOrderDishByOrder(Long orderId) {
        List<OrderDishEntity> orderDishEntityList = orderDishRepository.findAllByOrderId(orderId);
        return orderDishEntityMapper.toOrderDishModelList(orderDishEntityList);
    }
}