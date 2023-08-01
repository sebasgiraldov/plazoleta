package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderDishRequestDto;
import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.application.handler.IOrderDishHandler;
import com.pragma.powerup.application.mapper.IOrderDishRequestMapper;
import com.pragma.powerup.application.mapper.IOrderDishResponseMapper;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IOrderDishServicePort;
import com.pragma.powerup.domain.api.IOrderServicePort;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.domain.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderDishHandler implements IOrderDishHandler {

    private final IOrderDishServicePort orderDishServicePort;
    private final IOrderDishRequestMapper orderDishRequestMapper;
    private final IOrderDishResponseMapper orderDishResponseMapper;
    private final IDishServicePort dishServicePort;
    private final IOrderServicePort orderServicePort;

    @Override
    public OrderDishResponseDto createOrderDish(OrderDishRequestDto orderDishRequestDto, Long orderId) {
        DishModel dishModel = dishServicePort.getDish(orderDishRequestDto.getDishId());
        OrderModel orderModel = orderServicePort.getOrder(orderId);

        OrderDishModel orderDishModel = orderDishRequestMapper.toOrderDish(orderDishRequestDto);
        orderDishModel.setDishId(dishModel);
        orderDishModel.setOrderId(orderModel);

        orderDishServicePort.createOrderDish(orderDishModel);
        return orderDishResponseMapper.toResponse(orderDishModel);
    }

    @Override
    public OrderDishResponseDto getOrderDish(Long orderDishId) {
        OrderDishModel orderDishModel = orderDishServicePort.getOrderDish(orderDishId);
        return orderDishResponseMapper.toResponse(orderDishModel);
    }

    @Override
    public List<OrderDishResponseDto> getAllOrderDishByOrder(Long orderId) {
        return orderDishResponseMapper.toResponseList(orderDishServicePort.getAllOrderDishByOrder(orderId));
    }
}
