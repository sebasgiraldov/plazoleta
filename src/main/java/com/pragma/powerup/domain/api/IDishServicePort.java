package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {
    DishModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDish(Long dishId);

    void updateDish(DishModel dishModel);
    List<DishModel> getAllDishesByRestaurant(int pageN, int size, Long restaurantId);
}
