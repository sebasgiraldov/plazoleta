package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.AllRestaurantResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T15:59:24-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.1.1.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IRestaurantResponseMapperImpl implements IRestaurantResponseMapper {

    @Override
    public RestaurantResponseDto toResponse(RestaurantModel restaurantModel) {
        if ( restaurantModel == null ) {
            return null;
        }

        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setName( restaurantModel.getName() );
        restaurantResponseDto.setAddress( restaurantModel.getAddress() );
        restaurantResponseDto.setOwnerId( restaurantModel.getOwnerId() );
        restaurantResponseDto.setPhoneNumber( restaurantModel.getPhoneNumber() );
        restaurantResponseDto.setUrlLogo( restaurantModel.getUrlLogo() );
        restaurantResponseDto.setNit( restaurantModel.getNit() );

        return restaurantResponseDto;
    }

    @Override
    public List<AllRestaurantResponseDto> toResponseList(List<RestaurantModel> restaurantModels) {
        if ( restaurantModels == null ) {
            return null;
        }

        List<AllRestaurantResponseDto> list = new ArrayList<AllRestaurantResponseDto>( restaurantModels.size() );
        for ( RestaurantModel restaurantModel : restaurantModels ) {
            list.add( restaurantModelToAllRestaurantResponseDto( restaurantModel ) );
        }

        return list;
    }

    protected AllRestaurantResponseDto restaurantModelToAllRestaurantResponseDto(RestaurantModel restaurantModel) {
        if ( restaurantModel == null ) {
            return null;
        }

        AllRestaurantResponseDto allRestaurantResponseDto = new AllRestaurantResponseDto();

        allRestaurantResponseDto.setName( restaurantModel.getName() );
        allRestaurantResponseDto.setUrlLogo( restaurantModel.getUrlLogo() );

        return allRestaurantResponseDto;
    }
}
