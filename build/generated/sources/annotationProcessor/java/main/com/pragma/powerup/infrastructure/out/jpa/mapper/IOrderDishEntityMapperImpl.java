package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderDishModel;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderDishEntity;
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
public class IOrderDishEntityMapperImpl implements IOrderDishEntityMapper {

    @Override
    public OrderDishEntity toOrderDishEntity(OrderDishModel orderDishModel) {
        if ( orderDishModel == null ) {
            return null;
        }

        OrderDishEntity orderDishEntity = new OrderDishEntity();

        orderDishEntity.setOrderId( orderModelToOrderEntity( orderDishModel.getOrderId() ) );
        orderDishEntity.setId( orderDishModel.getId() );
        orderDishEntity.setDishId( dishModelToDishEntity( orderDishModel.getDishId() ) );
        orderDishEntity.setAmount( orderDishModel.getAmount() );

        return orderDishEntity;
    }

    @Override
    public OrderDishModel toOrderDishModel(OrderDishEntity orderDishEntity) {
        if ( orderDishEntity == null ) {
            return null;
        }

        OrderDishModel orderDishModel = new OrderDishModel();

        orderDishModel.setOrderId( orderEntityToOrderModel( orderDishEntity.getOrderId() ) );
        orderDishModel.setId( orderDishEntity.getId() );
        orderDishModel.setDishId( dishEntityToDishModel( orderDishEntity.getDishId() ) );
        orderDishModel.setAmount( orderDishEntity.getAmount() );

        return orderDishModel;
    }

    @Override
    public List<OrderDishModel> toOrderDishModelList(List<OrderDishEntity> orderDishEntityList) {
        if ( orderDishEntityList == null ) {
            return null;
        }

        List<OrderDishModel> list = new ArrayList<OrderDishModel>( orderDishEntityList.size() );
        for ( OrderDishEntity orderDishEntity : orderDishEntityList ) {
            list.add( toOrderDishModel( orderDishEntity ) );
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

    protected OrderEntity orderModelToOrderEntity(OrderModel orderModel) {
        if ( orderModel == null ) {
            return null;
        }

        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setId( orderModel.getId() );
        orderEntity.setClientId( orderModelClientIdId( orderModel ) );
        orderEntity.setChefId( orderModelChefIdId( orderModel ) );
        orderEntity.setDate( orderModel.getDate() );
        orderEntity.setOrderState( orderModel.getOrderState() );
        orderEntity.setRestaurantId( restaurantModelToRestaurantEntity( orderModel.getRestaurantId() ) );

        return orderEntity;
    }

    protected CategoryEntity categoryModelToCategoryEntity(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setId( categoryModel.getId() );
        categoryEntity.setName( categoryModel.getName() );
        categoryEntity.setDescription( categoryModel.getDescription() );

        return categoryEntity;
    }

    protected DishEntity dishModelToDishEntity(DishModel dishModel) {
        if ( dishModel == null ) {
            return null;
        }

        DishEntity dishEntity = new DishEntity();

        dishEntity.setId( dishModel.getId() );
        dishEntity.setName( dishModel.getName() );
        dishEntity.setCategoryId( categoryModelToCategoryEntity( dishModel.getCategoryId() ) );
        dishEntity.setDescription( dishModel.getDescription() );
        dishEntity.setPrice( dishModel.getPrice() );
        dishEntity.setRestaurantId( restaurantModelToRestaurantEntity( dishModel.getRestaurantId() ) );
        dishEntity.setUrlImage( dishModel.getUrlImage() );
        dishEntity.setActive( dishModel.getActive() );

        return dishEntity;
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

    protected OrderModel orderEntityToOrderModel(OrderEntity orderEntity) {
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

    protected CategoryModel categoryEntityToCategoryModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setId( categoryEntity.getId() );
        categoryModel.setName( categoryEntity.getName() );
        categoryModel.setDescription( categoryEntity.getDescription() );

        return categoryModel;
    }

    protected DishModel dishEntityToDishModel(DishEntity dishEntity) {
        if ( dishEntity == null ) {
            return null;
        }

        DishModel dishModel = new DishModel();

        dishModel.setId( dishEntity.getId() );
        dishModel.setName( dishEntity.getName() );
        dishModel.setCategoryId( categoryEntityToCategoryModel( dishEntity.getCategoryId() ) );
        dishModel.setDescription( dishEntity.getDescription() );
        dishModel.setPrice( dishEntity.getPrice() );
        dishModel.setRestaurantId( restaurantEntityToRestaurantModel( dishEntity.getRestaurantId() ) );
        dishModel.setUrlImage( dishEntity.getUrlImage() );
        dishModel.setActive( dishEntity.getActive() );

        return dishModel;
    }
}
