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
    date = "2023-07-24T12:42:10-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
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
        dishModel.setName( dishRequestDto.getName() );
        dishModel.setDescription( dishRequestDto.getDescription() );
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

        dishModel.setId( dishUpdateRequestDto.getId() );
        dishModel.setDescription( dishUpdateRequestDto.getDescription() );
        dishModel.setPrice( dishUpdateRequestDto.getPrice() );
        dishModel.setActive( dishUpdateRequestDto.isActive() );

        return dishModel;
    }

    @Override
    public CategoryModel toCategory(DishRequestDto dishRequestDto) {
        if ( dishRequestDto == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setName( dishRequestDto.getName() );
        categoryModel.setDescription( dishRequestDto.getDescription() );

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
