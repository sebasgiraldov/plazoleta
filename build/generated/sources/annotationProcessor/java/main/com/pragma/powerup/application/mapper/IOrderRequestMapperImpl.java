package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T15:59:24-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IOrderRequestMapperImpl implements IOrderRequestMapper {

    @Override
    public OrderModel toOrderModel(OrderRequestDto orderRequestDto) {
        if ( orderRequestDto == null ) {
            return null;
        }

        OrderModel orderModel = new OrderModel();

        orderModel.setRestaurantId( orderRequestDtoToRestaurantModel( orderRequestDto ) );

        return orderModel;
    }

    protected RestaurantModel orderRequestDtoToRestaurantModel(OrderRequestDto orderRequestDto) {
        if ( orderRequestDto == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId( orderRequestDto.getRestaurantId() );

        return restaurantModel;
    }
}
