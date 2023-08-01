package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderEntity;
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
public class IOrderEntityMapperImpl implements IOrderEntityMapper {

    @Override
    public OrderEntity toOrderEntity(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setClientId( orderModelClientIdId( orderModel ) );
        orderEntity.setChefId( orderModelChefIdId( orderModel ) );
        orderEntity.setId( orderModel.getId() );
        orderEntity.setDate( orderModel.getDate() );
        orderEntity.setOrderState( orderModel.getOrderState() );
        orderEntity.setRestaurantId( restaurantModelToRestaurantEntity( orderModel.getRestaurantId() ) );

        return orderEntity;
    }

    @Override
    public OrderModel toOrderModel(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        OrderModel orderModel = new OrderModel();

        orderModel.setClientId( orderEntityToUserModel( orderEntity ) );
        orderModel.setChefId( orderEntityToUserModel1( orderEntity ) );
        orderModel.setId( orderEntity.getId() );
        orderModel.setDate( orderEntity.getDate() );
        orderModel.setOrderState( orderEntity.getOrderState() );
        orderModel.setRestaurantId( restaurantEntityToRestaurantModel( orderEntity.getRestaurantId() ) );

        return orderModel;
    }

    @Override
    public List<OrderModel> toOrderModelList(List<OrderEntity> orderEntities) {
        if ( orderEntities == null ) {
            return null;
        }

        List<OrderModel> list = new ArrayList<OrderModel>( orderEntities.size() );
        for ( OrderEntity orderEntity : orderEntities ) {
            list.add( toOrderModel( orderEntity ) );
        }

        return list;
    }

    private Long orderModelClientIdId(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }
        UserModel clientId = orderModel.getClientId();
        if ( clientId == null ) {
            return null;
        }
        Long id = clientId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long orderModelChefIdId(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }
        UserModel chefId = orderModel.getChefId();
        if ( chefId == null ) {
            return null;
        }
        Long id = chefId.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    protected UserModel orderEntityToUserModel(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.id( orderEntity.getClientId() );

        return userModel.build();
    }

    protected UserModel orderEntityToUserModel1(OrderEntity orderEntity) {
        if ( orderEntity == null ) {
            return null;
        }

        UserModel.UserModelBuilder userModel = UserModel.builder();

        userModel.id( orderEntity.getChefId() );

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
