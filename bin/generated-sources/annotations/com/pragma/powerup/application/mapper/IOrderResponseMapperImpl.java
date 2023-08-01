package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.domain.model.OrderModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T12:14:59-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230622-1425, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class IOrderResponseMapperImpl implements IOrderResponseMapper {

    @Autowired
    private IRestaurantResponseMapper iRestaurantResponseMapper;

    @Override
    public OrderResponseDto toResponse(OrderModel orderModel, List<OrderDishResponseDto> orderDishResponseDtoList) {
        if ( orderModel == null && orderDishResponseDtoList == null ) {
            return null;
        }

        OrderResponseDto orderResponseDto = new OrderResponseDto();

        if ( orderModel != null ) {
            orderResponseDto.setOrderId( orderModel.getId() );
            orderResponseDto.setDate( orderModel.getDate() );
            orderResponseDto.setOrderState( orderModel.getOrderState() );
            orderResponseDto.setRestaurantId( iRestaurantResponseMapper.toResponse( orderModel.getRestaurantId() ) );
        }
        List<OrderDishResponseDto> list = orderDishResponseDtoList;
        if ( list != null ) {
            orderResponseDto.setOrders( new ArrayList<OrderDishResponseDto>( list ) );
        }

        return orderResponseDto;
    }
}
