package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.OrderDishModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T12:42:10-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IOrderDishResponseMapperImpl implements IOrderDishResponseMapper {

    @Autowired
    private ICategoryResponseMapper iCategoryResponseMapper;
    @Autowired
    private IRestaurantResponseMapper iRestaurantResponseMapper;

    @Override
    public OrderDishResponseDto toResponse(OrderDishModel orderDishModel) {
        if ( orderDishModel == null ) {
            return null;
        }

        OrderDishResponseDto orderDishResponseDto = new OrderDishResponseDto();

        orderDishResponseDto.setDishId( dishModelToDishResponseDto( orderDishModel.getDishId() ) );
        orderDishResponseDto.setAmount( orderDishModel.getAmount() );

        return orderDishResponseDto;
    }

    @Override
    public List<OrderDishResponseDto> toResponseList(List<OrderDishModel> orderDishModelList) {
        if ( orderDishModelList == null ) {
            return null;
        }

        List<OrderDishResponseDto> list = new ArrayList<OrderDishResponseDto>( orderDishModelList.size() );
        for ( OrderDishModel orderDishModel : orderDishModelList ) {
            list.add( toResponse( orderDishModel ) );
        }

        return list;
    }

    protected DishResponseDto dishModelToDishResponseDto(DishModel dishModel) {
        if ( dishModel == null ) {
            return null;
        }

        DishResponseDto dishResponseDto = new DishResponseDto();

        dishResponseDto.setName( dishModel.getName() );
        dishResponseDto.setCategoryId( iCategoryResponseMapper.toResponse( dishModel.getCategoryId() ) );
        dishResponseDto.setDescription( dishModel.getDescription() );
        dishResponseDto.setPrice( dishModel.getPrice() );
        dishResponseDto.setRestaurantId( iRestaurantResponseMapper.toResponse( dishModel.getRestaurantId() ) );
        dishResponseDto.setUrlImage( dishModel.getUrlImage() );
        dishResponseDto.setActive( dishModel.getActive() );

        return dishResponseDto;
    }
}
