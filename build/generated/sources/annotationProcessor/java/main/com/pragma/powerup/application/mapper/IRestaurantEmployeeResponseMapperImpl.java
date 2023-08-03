package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.RestaurantEmployeeResponseDto;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T15:59:24-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IRestaurantEmployeeResponseMapperImpl implements IRestaurantEmployeeResponseMapper {

    @Override
    public RestaurantEmployeeResponseDto toResponse(RestaurantEmployeeModel restaurantEmployeeModel) {
        if ( restaurantEmployeeModel == null ) {
            return null;
        }

        RestaurantEmployeeResponseDto restaurantEmployeeResponseDto = new RestaurantEmployeeResponseDto();

        restaurantEmployeeResponseDto.setRestaurantId( restaurantEmployeeModelRestaurantIdId( restaurantEmployeeModel ) );
        restaurantEmployeeResponseDto.setEmployeeId( restaurantEmployeeModelEmployeeIdId( restaurantEmployeeModel ) );
        restaurantEmployeeResponseDto.setField( restaurantEmployeeModel.getField() );

        return restaurantEmployeeResponseDto;
    }

    private Long restaurantEmployeeModelRestaurantIdId(RestaurantEmployeeModel restaurantEmployeeModel) {
        if ( restaurantEmployeeModel == null ) {
            return null;
        }
        RestaurantModel restaurantId = restaurantEmployeeModel.getRestaurantId();
        if ( restaurantId == null ) {
            return null;
        }
        Long id = restaurantId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long restaurantEmployeeModelEmployeeIdId(RestaurantEmployeeModel restaurantEmployeeModel) {
        if ( restaurantEmployeeModel == null ) {
            return null;
        }
        UserModel employeeId = restaurantEmployeeModel.getEmployeeId();
        if ( employeeId == null ) {
            return null;
        }
        Long id = employeeId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
