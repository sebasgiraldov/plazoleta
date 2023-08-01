package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantServicePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    RestaurantModel getRestaurant(Long restaurantId);

    List<RestaurantModel> getAllRestaurants(int pageN, int size);
    List<RestaurantModel> getAllRestaurants();
}
