package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.request.ListPaginationRequest;
import com.pragma.powerup.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {

    DishResponseDto saveDish(DishRequestDto dishRequestDto);

    List<DishResponseDto> getAllDishes();

    DishResponseDto getDish(Long dishId);

    DishResponseDto updateDish(DishUpdateRequestDto dishUpdateRequestDto);

    DishResponseDto enableDish(Long dishId);

    List<DishResponseDto> getAllDishesByRestaurant(ListPaginationRequest listPaginationRequest, Long restaurantId);

}
