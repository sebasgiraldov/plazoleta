package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-03T10:55:35-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class IRestaurantEmployeeRequestMapperImpl implements IRestaurantEmployeeRequestMapper {

    @Override
    public RestaurantEmployeeModel toRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        if ( restaurantEmployeeRequestDto == null ) {
            return null;
        }

        RestaurantEmployeeModel restaurantEmployeeModel = new RestaurantEmployeeModel();

        restaurantEmployeeModel.setRestaurantId( restaurantEmployeeRequestDtoToRestaurantModel( restaurantEmployeeRequestDto ) );
        restaurantEmployeeModel.setEmployeeId( restaurantEmployeeRequestDtoToUserModel( restaurantEmployeeRequestDto ) );
        restaurantEmployeeModel.setField( restaurantEmployeeRequestDto.getField() );

        return restaurantEmployeeModel;
    }

    protected RestaurantModel restaurantEmployeeRequestDtoToRestaurantModel(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        if ( restaurantEmployeeRequestDto == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId( restaurantEmployeeRequestDto.getRestaurantId() );

        return restaurantModel;
    }

    protected UserModel restaurantEmployeeRequestDtoToUserModel(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        if ( restaurantEmployeeRequestDto == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.id( restaurantEmployeeRequestDto.getEmployeeId() );

        return userModel.build();
    }
}
