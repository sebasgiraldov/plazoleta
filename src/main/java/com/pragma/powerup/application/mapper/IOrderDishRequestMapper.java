package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderDishRequestDto;
import com.pragma.powerup.domain.model.OrderDishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderDishRequestMapper {
    @Mapping(source = "orderDishRequest.dishId", target = "dishId.id")
    OrderDishModel toOrderDish(OrderDishRequestDto orderDishRequest);
}
