package com.pragma.powerup.domain.spi;


import com.pragma.powerup.domain.model.RestaurantEmployeeModel;

public interface IRestaurantEmployeePersistencePort {
    RestaurantEmployeeModel saveRestaurantEmployee(RestaurantEmployeeModel restaurantEmployeeModel);

    RestaurantEmployeeModel getRestaurantEmployee(Long restaurantEmployeeId);

    RestaurantEmployeeModel getRestaurantByEmployeeId(Long employeeId);
}