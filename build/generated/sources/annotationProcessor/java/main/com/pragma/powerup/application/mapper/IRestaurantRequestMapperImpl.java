package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T15:59:24-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IRestaurantRequestMapperImpl implements IRestaurantRequestMapper {

    @Override
    public RestaurantModel toRestaurant(RestaurantRequestDto restaurantRequestDto) {
        if ( restaurantRequestDto == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setName( restaurantRequestDto.getName() );
        restaurantModel.setAddress( restaurantRequestDto.getAddress() );
        restaurantModel.setOwnerId( restaurantRequestDto.getOwnerId() );
        restaurantModel.setPhoneNumber( restaurantRequestDto.getPhoneNumber() );
        restaurantModel.setUrlLogo( restaurantRequestDto.getUrlLogo() );
        restaurantModel.setNit( restaurantRequestDto.getNit() );

        return restaurantModel;
    }
}
