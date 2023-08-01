package com.pragma.powerup.domain.spi;


import com.pragma.powerup.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {

    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    RestaurantModel getRestaurant(Long restaurantId);
    List<RestaurantModel> getAllRestaurants(int pageN,int size);
    List<RestaurantModel> getAllRestaurants();

}
