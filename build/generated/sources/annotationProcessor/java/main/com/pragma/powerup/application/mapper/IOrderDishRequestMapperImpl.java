package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderDishRequestDto;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderDishModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T15:59:24-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IOrderDishRequestMapperImpl implements IOrderDishRequestMapper {

    @Override
    public OrderDishModel toOrderDish(OrderDishRequestDto orderDishRequest) {
        if ( orderDishRequest == null ) {
            return null;
        }

        OrderDishModel orderDishModel = new OrderDishModel();

        orderDishModel.setDishId( orderDishRequestDtoToDishModel( orderDishRequest ) );
        orderDishModel.setAmount( orderDishRequest.getAmount() );

        return orderDishModel;
    }

    protected DishModel orderDishRequestDtoToDishModel(OrderDishRequestDto orderDishRequestDto) {
        if ( orderDishRequestDto == null ) {
            return null;
        }

        DishModel dishModel = new DishModel();

        dishModel.setId( orderDishRequestDto.getDishId() );

        return dishModel;
    }
}
