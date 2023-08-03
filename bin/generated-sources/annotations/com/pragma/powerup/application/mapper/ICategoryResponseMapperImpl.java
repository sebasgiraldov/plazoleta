package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.domain.model.CategoryModel;
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
public class ICategoryResponseMapperImpl implements ICategoryResponseMapper {

    @Override
    public CategoryResponseDto toResponse(CategoryModel categoryModel) {
        if ( categoryModel == null ) {
            return null;
        }

        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();

        categoryResponseDto.setDescription( categoryModel.getDescription() );
        categoryResponseDto.setName( categoryModel.getName() );

        return categoryResponseDto;
    }

    @Override
    public List<CategoryResponseDto> toResponseList(List<CategoryModel> categoryModels) {
        if ( categoryModels == null ) {
            return null;
        }

        List<CategoryResponseDto> list = new ArrayList<CategoryResponseDto>( categoryModels.size() );
        for ( CategoryModel categoryModel : categoryModels ) {
            list.add( toResponse( categoryModel ) );
        }

        return list;
    }
}
