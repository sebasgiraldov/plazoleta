package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantEmployeeEntityMapper {
    @Mapping(source = "restaurantEmployeeModel.restaurantId.id", target = "restaurantId.id")
    @Mapping(source = "restaurantEmployeeModel.employeeId.id", target = "employeeId")
    RestaurantEmployeeEntity toEntity(RestaurantEmployeeModel restaurantEmployeeModel);

    @Mapping(source = "restaurantEmployeeEntity.employeeId", target = "employeeId.id")
    RestaurantEmployeeModel toModel(RestaurantEmployeeEntity restaurantEmployeeEntity);

    List<RestaurantEmployeeModel> toModelList(List<RestaurantEmployeeEntity> restaurantEmployeeEntityList);
}
