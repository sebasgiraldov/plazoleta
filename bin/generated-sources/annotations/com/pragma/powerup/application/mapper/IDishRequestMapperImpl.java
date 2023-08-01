package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T12:14:59-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230622-1425, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class IDishRequestMapperImpl implements IDishRequestMapper {

    @Override
    public DishModel toDish(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        DishModel dishModel = new DishModel();

        dishModel.setCategoryId( dishRequestDtoToCategoryModel( dishRequestDto ) );
        dishModel.setRestaurantId( dishRequestDtoToRestaurantModel( dishRequestDto ) );
        dishModel.setDescription( dishRequestDto.getDescription() );
        dishModel.setName( dishRequestDto.getName() );
        dishModel.setPrice( dishRequestDto.getPrice() );
        dishModel.setUrlImage( dishRequestDto.getUrlImage() );

        return dishModel;
    }

    @Override
    public DishModel toDish(DishUpdateRequestDto dishUpdateRequestDto) {
        if ( dishUpdateRequestDto == null ) {
            return null;
        }

        DishModel dishModel = new DishModel();

        dishModel.setActive( dishUpdateRequestDto.isActive() );
        dishModel.setDescription( dishUpdateRequestDto.getDescription() );
        dishModel.setId( dishUpdateRequestDto.getId() );
        dishModel.setPrice( dishUpdateRequestDto.getPrice() );

        return dishModel;
    }

    @Override
    public CategoryModel toCategory(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setDescription( dishRequestDto.getDescription() );
        categoryModel.setName( dishRequestDto.getName() );

        return categoryModel;
    }

    @Override
    public RestaurantModel toRestaurant(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setName( dishRequestDto.getName() );

        return restaurantModel;
    }

    protected CategoryModel dishRequestDtoToCategoryModel(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setId( dishRequestDto.getCategoryId() );

        return categoryModel;
    }

    protected RestaurantModel dishRequestDtoToRestaurantModel(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId( dishRequestDto.getRestaurantId() );

        return restaurantModel;
    }
}
