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
    date = "2023-08-02T15:59:24-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
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
