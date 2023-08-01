package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T12:42:11-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IRestaurantEmployeeEntityMapperImpl implements IRestaurantEmployeeEntityMapper {

    @Override
    public RestaurantEmployeeEntity toEntity(RestaurantEmployeeModel restaurantEmployeeModel) {
        if ( restaurantEmployeeModel == null ) {
            return null;
        }

        RestaurantEmployeeEntity restaurantEmployeeEntity = new RestaurantEmployeeEntity();

        restaurantEmployeeEntity.setRestaurantId( restaurantModelToRestaurantEntity( restaurantEmployeeModel.getRestaurantId() ) );
        restaurantEmployeeEntity.setEmployeeId( restaurantEmployeeModelEmployeeIdId( restaurantEmployeeModel ) );
        restaurantEmployeeEntity.setId( restaurantEmployeeModel.getId() );
        restaurantEmployeeEntity.setField( restaurantEmployeeModel.getField() );

        return restaurantEmployeeEntity;
    }

    @Override
    public RestaurantEmployeeModel toModel(RestaurantEmployeeEntity restaurantEmployeeEntity) {
        if ( restaurantEmployeeEntity == null ) {
            return null;
        }

        RestaurantEmployeeModel restaurantEmployeeModel = new RestaurantEmployeeModel();

        restaurantEmployeeModel.setEmployeeId( restaurantEmployeeEntityToUserModel( restaurantEmployeeEntity ) );
        restaurantEmployeeModel.setId( restaurantEmployeeEntity.getId() );
        restaurantEmployeeModel.setRestaurantId( restaurantEntityToRestaurantModel( restaurantEmployeeEntity.getRestaurantId() ) );
        restaurantEmployeeModel.setField( restaurantEmployeeEntity.getField() );

        return restaurantEmployeeModel;
    }

    @Override
    public List<RestaurantEmployeeModel> toModelList(List<RestaurantEmployeeEntity> restaurantEmployeeEntityList) {
        if ( restaurantEmployeeEntityList == null ) {
            return null;
        }

        List<RestaurantEmployeeModel> list = new ArrayList<RestaurantEmployeeModel>( restaurantEmployeeEntityList.size() );
        for ( RestaurantEmployeeEntity restaurantEmployeeEntity : restaurantEmployeeEntityList ) {
            list.add( toModel( restaurantEmployeeEntity ) );
        }

        return list;
    }

    protected RestaurantEntity restaurantModelToRestaurantEntity(RestaurantModel restaurantModel) {
        if ( restaurantModel == null ) {
            return null;
        }

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setId( restaurantModel.getId() );
        restaurantEntity.setName( restaurantModel.getName() );
        restaurantEntity.setAddress( restaurantModel.getAddress() );
        restaurantEntity.setOwnerId( restaurantModel.getOwnerId() );
        restaurantEntity.setPhoneNumber( restaurantModel.getPhoneNumber() );
        restaurantEntity.setUrlLogo( restaurantModel.getUrlLogo() );
        restaurantEntity.setNit( restaurantModel.getNit() );

        return restaurantEntity;
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

    protected UserModel restaurantEmployeeEntityToUserModel(RestaurantEmployeeEntity restaurantEmployeeEntity) {
        if ( restaurantEmployeeEntity == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.id( restaurantEmployeeEntity.getEmployeeId() );

        return userModel.build();
    }

    protected RestaurantModel restaurantEntityToRestaurantModel(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId( restaurantEntity.getId() );
        restaurantModel.setName( restaurantEntity.getName() );
        restaurantModel.setAddress( restaurantEntity.getAddress() );
        restaurantModel.setOwnerId( restaurantEntity.getOwnerId() );
        restaurantModel.setPhoneNumber( restaurantEntity.getPhoneNumber() );
        restaurantModel.setUrlLogo( restaurantEntity.getUrlLogo() );
        restaurantModel.setNit( restaurantEntity.getNit() );

        return restaurantModel;
    }
}
