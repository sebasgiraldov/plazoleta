package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.CategoryEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-03T10:55:35-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ICategoryEntityMapperImpl implements ICategoryEntityMapper {

    @Override
    public CategoryEntity toEntity(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setDescription( categoryModel.getDescription() );
        categoryEntity.setId( categoryModel.getId() );
        categoryEntity.setName( categoryModel.getName() );

        return categoryEntity;
    }

    @Override
    public CategoryModel toCategoryModel(CategoryEntity categoryEntity) {
        if ( categoryEntity == null ) {
            return null;
        }

        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setDescription( categoryEntity.getDescription() );
        categoryModel.setId( categoryEntity.getId() );
        categoryModel.setName( categoryEntity.getName() );

        return categoryModel;
    }

    @Override
    public List<CategoryModel> toCategoryModelList(List<CategoryEntity> categoryEntityList) {
        if ( categoryEntityList == null ) {
            return null;
        }

        List<CategoryModel> list = new ArrayList<CategoryModel>( categoryEntityList.size() );
        for ( CategoryEntity categoryEntity : categoryEntityList ) {
            list.add( toCategoryModel( categoryEntity ) );
        }

        return list;
    }
}
