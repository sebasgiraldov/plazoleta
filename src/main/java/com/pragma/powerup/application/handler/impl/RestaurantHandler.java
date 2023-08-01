package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.ListPaginationRequest;
import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.AllRestaurantResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.handler.IRestaurantHandler;
import com.pragma.powerup.application.mapper.IRestaurantRequestMapper;
import com.pragma.powerup.application.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infrastructure.input.rest.client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;
    private final IUserClient userClient;

    @Override
    public RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurantModel = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        Object userRequestDto = userClient.getUserById(restaurantModel.getOwnerId()).getBody().getData();
        restaurantModel.setOwnerId(restaurantRequestDto.getOwnerId());
        return restaurantResponseMapper.toResponse(restaurantServicePort.saveRestaurant(restaurantModel));
    }

    @Override
    public List<AllRestaurantResponseDto> getAllRestaurants(ListPaginationRequest listPaginationRequest) {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants(listPaginationRequest.getPageN(), listPaginationRequest.getSize()));
    }


    @Override
    public List<AllRestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }
}
