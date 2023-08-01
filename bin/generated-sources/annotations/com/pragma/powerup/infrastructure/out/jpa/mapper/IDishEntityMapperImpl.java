package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-21T12:14:59-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230622-1425, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class IDishEntityMapperImpl implements IDishEntityMapper {

    @Override
    public DishEntity toEntity(DishModel dishModel) {
        if ( dishModel == null ) {
            return null;
        }

        DishEntity dishEntity = new DishEntity();

        dishEntity.setActive( dishModel.getActive() );
        dishEntity.setCategoryId( categoryModelToCategoryEntity( dishModel.getCategoryId() ) );
        dishEntity.setDescription( dishModel.getDescription() );
        dishEntity.setId( dishModel.getId() );
        dishEntity.setName( dishModel.getName() );
        dishEntity.setPrice( dishModel.getPrice() );
        dishEntity.setRestaurantId( restaurantModelToRestaurantEntity( dishModel.getRestaurantId() ) );
        dishEntity.setUrlImage( dishModel.getUrlImage() );

        return dishEntity;
    }

    @Override
    public DishModel toDishModel(DishEntity dishEntity) {
        if ( dishEntity == null ) {
            return null;
        }

        DishModel dishModel = new DishModel();

        dishModel.setActive( dishEntity.getActive() );
        dishModel.setCategoryId( categoryEntityToCategoryModel( dishEntity.getCategoryId() ) );
        dishModel.setDescription( dishEntity.getDescription() );
        dishModel.setId( dishEntity.getId() );
        dishModel.setName( dishEntity.getName() );
        dishModel.setPrice( dishEntity.getPrice() );
        dishModel.setRestaurantId( restaurantEntityToRestaurantModel( dishEntity.getRestaurantId() ) );
        dishModel.setUrlImage( dishEntity.getUrlImage() );

        return dishModel;
    }

    @Override
    public List<DishModel> toDishModelList(List<DishEntity> dishEntityList) {
        if ( dishEntityList == null ) {
            return null;
        }

        List<DishModel> list = new ArrayList<DishModel>( dishEntityList.size() );
        for ( DishEntity dishEntity : dishEntityList ) {
            list.add( toDishModel( dishEntity ) );
        }

        return list;
    }

    protected CategoryEntity categoryModelToCategoryEntity(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setDescription( categoryModel.getDescription() );
        categoryEntity.setId( categoryModel.getId() );
        categoryEntity.setName( categoryModel.getName() );

        return categoryEntity;
    }

    protected RestaurantEntity restaurantModelToRestaurantEntity(RestaurantModel restaurantModel) {
        if ( restaurantModel == null ) {
            return null;
        }

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setAddress( restaurantModel.getAddress() );
        restaurantEntity.setId( restaurantModel.getId() );
        restaurantEntity.setName( restaurantModel.getName() );
        restaurantEntity.setNit( restaurantModel.getNit() );
        restaurantEntity.setOwnerId( restaurantModel.getOwnerId() );
        restaurantEntity.setPhoneNumber( restaurantModel.getPhoneNumber() );
        restaurantEntity.setUrlLogo( restaurantModel.getUrlLogo() );

        return restaurantEntity;
    }

    protected CategoryModel categoryEntityToCategoryModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setDescription( categoryEntity.getDescription() );
        categoryModel.setId( categoryEntity.getId() );
        categoryModel.setName( categoryEntity.getName() );

        return categoryModel;
    }

    protected RestaurantModel restaurantEntityToRestaurantModel(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setAddress( restaurantEntity.getAddress() );
        restaurantModel.setId( restaurantEntity.getId() );
        restaurantModel.setName( restaurantEntity.getName() );
        restaurantModel.setNit( restaurantEntity.getNit() );
        restaurantModel.setOwnerId( restaurantEntity.getOwnerId() );
        restaurantModel.setPhoneNumber( restaurantEntity.getPhoneNumber() );
        restaurantModel.setUrlLogo( restaurantEntity.getUrlLogo() );

        return restaurantModel;
    }
}
