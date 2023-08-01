package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantEmployeeResponseDto;
import com.pragma.powerup.application.handler.IRestaurantEmployeeHandler;
import com.pragma.powerup.application.mapper.IRestaurantEmployeeRequestMapper;
import com.pragma.powerup.application.mapper.IRestaurantEmployeeResponseMapper;
import com.pragma.powerup.domain.api.IRestaurantEmployeeServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infrastructure.exception.NotEnoughPrivileges;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantEmployeeHandler implements IRestaurantEmployeeHandler {

    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    private final IRestaurantEmployeeRequestMapper restaurantEmployeeRequestMapper;
    private final IRestaurantEmployeeResponseMapper restaurantEmployeeResponseMapper;
    private final IRestaurantServicePort restaurantServicePort;

    @Override
    public RestaurantEmployeeResponseDto saveRestaurantEmployee(RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {

        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(restaurantEmployeeRequestDto.getRestaurantId());
        if (!restaurantModel.getOwnerId().equals(restaurantEmployeeRequestDto.getOwnerId())) {
            throw new NotEnoughPrivileges();
        }

        RestaurantEmployeeModel restaurantEmployeeModel = restaurantEmployeeRequestMapper.toRestaurantEmployee(restaurantEmployeeRequestDto);
        return restaurantEmployeeResponseMapper.toResponse(restaurantEmployeeServicePort.saveRestaurantEmployee(restaurantEmployeeModel));
    }

    @Override
    public RestaurantEmployeeResponseDto getRestaurantEmployee(Long restaurantEmployeeId) {
        RestaurantEmployeeModel restaurantEmployeeModel = restaurantEmployeeServicePort.getRestaurantEmployee(restaurantEmployeeId);
        return restaurantEmployeeResponseMapper.toResponse(restaurantEmployeeModel);
    }
}
