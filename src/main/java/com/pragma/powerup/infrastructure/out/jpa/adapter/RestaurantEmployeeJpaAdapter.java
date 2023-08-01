package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.spi.IRestaurantEmployeePersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IRestaurantEmployeeEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantEmployeeJpaAdapter implements IRestaurantEmployeePersistencePort {

    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final IRestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

    @Override
    public RestaurantEmployeeModel saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel) {
        RestaurantEmployeeEntity restaurantEmployeeEntity = restaurantEmployeeRepository.save(restaurantEmployeeEntityMapper.toEntity(restaurantEmployeeModel));
        return restaurantEmployeeEntityMapper.toModel(restaurantEmployeeEntity);
    }

    @Override
    public RestaurantEmployeeModel getRestaurantEmployee(Long restaurantEmployeeId) {
        return restaurantEmployeeEntityMapper.toModel(restaurantEmployeeRepository.findById(restaurantEmployeeId).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public RestaurantEmployeeModel getRestaurantByEmployeeId(Long employeeId) {
        return restaurantEmployeeEntityMapper.toModel(restaurantEmployeeRepository.findFirstRestaurantEmployeeByEmployeeId(employeeId));
    }
}
