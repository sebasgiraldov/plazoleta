package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.ListPaginationRequest;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.AllRestaurantResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    List<AllRestaurantResponseDto> getAllRestaurants(ListPaginationRequest listPaginationRequest);

    List<AllRestaurantResponseDto> getAllRestaurants();

}
