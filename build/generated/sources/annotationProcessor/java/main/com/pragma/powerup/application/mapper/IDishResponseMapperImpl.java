package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.DishModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T12:42:10-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IDishResponseMapperImpl implements IDishResponseMapper {

    @Override
    public DishResponseDto toResponse(DishModel dishModel, CategoryResponseDto categoryResponseDto, RestaurantResponseDto restaurantResponseDto) {
        if ( dishModel == null && categoryResponseDto == null && restaurantResponseDto == null ) {
            return null;
        }

        DishResponseDto dishResponseDto = new DishResponseDto();

        if ( dishModel != null ) {
            dishResponseDto.setName( dishModel.getName() );
            dishResponseDto.setDescription( dishModel.getDescription() );
            dishResponseDto.setPrice( dishModel.getPrice() );
            dishResponseDto.setUrlImage( dishModel.getUrlImage() );
            dishResponseDto.setActive( dishModel.getActive() );
        }
        dishResponseDto.setCategoryId( categoryResponseDtoToCategoryResponseDto( categoryResponseDto ) );
        dishResponseDto.setRestaurantId( restaurantResponseDtoToRestaurantResponseDto( restaurantResponseDto ) );

        return dishResponseDto;
    }

    protected CategoryResponseDto categoryResponseDtoToCategoryResponseDto(CategoryResponseDto categoryResponseDto) {
        if ( categoryResponseDto == null ) {
            return null;
        }

        CategoryResponseDto categoryResponseDto1 = new CategoryResponseDto();

        categoryResponseDto1.setName( categoryResponseDto.getName() );
        categoryResponseDto1.setDescription( categoryResponseDto.getDescription() );

        return categoryResponseDto1;
    }

    protected RestaurantResponseDto restaurantResponseDtoToRestaurantResponseDto(RestaurantResponseDto restaurantResponseDto) {
        if ( restaurantResponseDto == null ) {
            return null;
        }

        RestaurantResponseDto restaurantResponseDto1 = new RestaurantResponseDto();

        restaurantResponseDto1.setName( restaurantResponseDto.getName() );
        restaurantResponseDto1.setAddress( restaurantResponseDto.getAddress() );
        restaurantResponseDto1.setOwnerId( restaurantResponseDto.getOwnerId() );
        restaurantResponseDto1.setPhoneNumber( restaurantResponseDto.getPhoneNumber() );
        restaurantResponseDto1.setUrlLogo( restaurantResponseDto.getUrlLogo() );
        restaurantResponseDto1.setNit( restaurantResponseDto.getNit() );

        return restaurantResponseDto1;
    }
}
