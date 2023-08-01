package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.domain.model.OrderDishModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ICategoryResponseMapper.class, IRestaurantResponseMapper.class})
public interface IOrderDishResponseMapper {

    IDishResponseMapper DISH_RESPONSE_MAPPER = Mappers.getMapper(IDishResponseMapper.class);

    OrderDishResponseDto toResponse(OrderDishModel orderDishModel);

    List<OrderDishResponseDto> toResponseList(List<OrderDishModel> orderDishModelList);
}
