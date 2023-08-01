package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeRequestMapper {
    @Mapping(source = "restaurantEmployeeRequestDto.restaurantId", target = "restaurantId.id")
    @Mapping(source = "restaurantEmployeeRequestDto.employeeId", target = "employeeId.id")
    RestaurantEmployeeModel toRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);
}
