package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
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
public interface IDishResponseMapper {

    ICategoryResponseMapper CATEGORY_RESPONSE_MAPPER_INSTANCE = Mappers.getMapper(ICategoryResponseMapper.class);
    IRestaurantResponseMapper RESTAURANT_RESPONSE_MAPPER_INSTANCE = Mappers.getMapper(IRestaurantResponseMapper.class);

    @Mapping(target = "categoryId.name", source = "categoryResponseDto.name")
    @Mapping(target = "categoryId.description", source = "categoryResponseDto.description")
    @Mapping(target = "restaurantId.name", source = "restaurantResponseDto.name")
    @Mapping(target = "restaurantId.address", source = "restaurantResponseDto.address")
    @Mapping(target = "restaurantId.ownerId", source = "restaurantResponseDto.ownerId")
    @Mapping(target = "restaurantId.phoneNumber", source = "restaurantResponseDto.phoneNumber")
    @Mapping(target = "restaurantId.urlLogo", source = "restaurantResponseDto.urlLogo")
    @Mapping(target = "restaurantId.nit", source = "restaurantResponseDto.nit")
    @Mapping(target = "name", source = "dishModel.name")
    @Mapping(target = "description", source = "dishModel.description")
    DishResponseDto toResponse(DishModel dishModel, CategoryResponseDto categoryResponseDto, RestaurantResponseDto restaurantResponseDto);

    default List<DishResponseDto> toResponseList(List<DishModel> dishModelList, List<CategoryModel> categoryModelList, List<RestaurantModel> restaurantModelList) {
        return dishModelList.stream()
                .map(dish -> {
                    DishResponseDto dishResponseDto = new DishResponseDto();

                    dishResponseDto.setName(dish.getName());
                    dishResponseDto.setCategoryId(CATEGORY_RESPONSE_MAPPER_INSTANCE.toResponse(categoryModelList.stream().filter(
                            category -> category.getId().equals(dish.getCategoryId().getId())
                    ).findFirst().get()));

                    dishResponseDto.setDescription(dish.getDescription());
                    dishResponseDto.setPrice(dish.getPrice());

                    dishResponseDto.setRestaurantId(RESTAURANT_RESPONSE_MAPPER_INSTANCE.toResponse(restaurantModelList.stream().filter(
                            restaurant -> restaurant.getId().equals(dish.getRestaurantId().getId())
                    ).findFirst().get()));

                    dishResponseDto.setUrlImage(dish.getUrlImage());
                    dishResponseDto.setActive(dish.getActive());

                    return dishResponseDto;
                }).collect(Collectors.toList());
    }

}
