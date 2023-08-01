package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.OrderStateResponseDto;
import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ICategoryResponseMapper.class, IRestaurantResponseMapper.class})
public interface IOrderResponseMapper {

    IRestaurantResponseMapper RESTAURANT_RESPONSE_MAPPER_INSTANCE = Mappers.getMapper(IRestaurantResponseMapper.class);
    IOrderDishResponseMapper ORDER_DISH_RESPONSE_MAPPER = Mappers.getMapper(IOrderDishResponseMapper.class);

    @Mapping(source = "orderDishResponseDtoList", target = "orders")
    @Mapping(source = "orderModel.id", target = "orderId")
    OrderResponseDto toResponse(OrderModel orderModel, List<OrderDishResponseDto> orderDishResponseDtoList);

    default List<OrderStateResponseDto> toReponseList(List<OrderModel> orderModelList, List<RestaurantModel> restaurantModelList, List<OrderDishModel> orderDishModelList) {
        return orderModelList.stream()
                .map(order -> {
                    OrderStateResponseDto orderResponseDto = new OrderStateResponseDto();

                    orderResponseDto.setOrderId(order.getId());
                    orderResponseDto.setDate(order.getDate());
                    orderResponseDto.setOrderState(order.getOrderState());

                    orderResponseDto.setRestaurantId(RESTAURANT_RESPONSE_MAPPER_INSTANCE.toResponse(restaurantModelList.stream().filter(
                            restaurant -> restaurant.getId().equals(order.getRestaurantId().getId())
                    ).findFirst().get()));

                    List<OrderDishModel> orderDishModelListFiltered = orderDishModelList.stream().filter(
                            orderDishModel -> orderDishModel.getOrderId().getId().equals(order.getId())
                    ).collect(Collectors.toList());

                    List<Long> longList = orderDishModelListFiltered.stream().map(
                            OrderDishModel::getId
                    ).collect(Collectors.toList());

                    orderResponseDto.setOrderDishIds(longList);

                    return orderResponseDto;
                }).collect(Collectors.toList());
    }

}
