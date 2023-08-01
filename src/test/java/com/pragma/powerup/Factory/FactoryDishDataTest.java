package com.pragma.powerup.Factory;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;

public class FactoryDishDataTest {

    public static DishModel getDishModle() {
        DishModel expectedDishModel = new DishModel();

        expectedDishModel.setName("Arroz");
        expectedDishModel.setCategoryId(getCategoryModel());
        expectedDishModel.setDescription("restaurante");
        expectedDishModel.setPrice(200);
        expectedDishModel.setRestaurantId(getRestaurantModel());
        expectedDishModel.setUrlImage("urlImage");
        expectedDishModel.setActive(true);

        return expectedDishModel;
    }

    public static DishModel getDishModel2() {
        DishModel expectedDishModel = new DishModel();

        expectedDishModel.setName("Arroz");
        expectedDishModel.setCategoryId(getCategoryModel());
        expectedDishModel.setDescription("restaurante2");
        expectedDishModel.setPrice(200);
        expectedDishModel.setRestaurantId(getRestaurantModel());
        expectedDishModel.setUrlImage("urlImage");
        expectedDishModel.setActive(true);

        return expectedDishModel;
    }

    public static DishRequestDto getDishRequestDto() {
        DishRequestDto dishRequestDto = new DishRequestDto();

        dishRequestDto.setName("Arroz");
        dishRequestDto.setCategoryId(1L);
        dishRequestDto.setDescription("restaurante");
        dishRequestDto.setPrice(200);
        dishRequestDto.setRestaurantId(1L);
        dishRequestDto.setUrlImage("urlImage");

        return dishRequestDto;
    }

    public static CategoryModel getCategoryModel() {
        CategoryModel categoryModel = new CategoryModel();

        categoryModel.setId(1L);
        categoryModel.setName("categoria");
        categoryModel.setDescription("descripcion");

        return categoryModel;
    }

    public static RestaurantModel getRestaurantModel() {
        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId(1L);
        restaurantModel.setName("Subway");
        restaurantModel.setAddress("Calle 5");
        restaurantModel.setOwnerId(1L);
        restaurantModel.setPhoneNumber("+10000");
        restaurantModel.setUrlLogo("logoUrl");
        restaurantModel.setNit("20000");

        return restaurantModel;
    }

    public static RestaurantModel getRestaurantModel2() {
        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId(1L);
        restaurantModel.setName("Subway");
        restaurantModel.setAddress("Calle 5");
        restaurantModel.setOwnerId(2L);
        restaurantModel.setPhoneNumber("+10000");
        restaurantModel.setUrlLogo("logoUrl");
        restaurantModel.setNit("20000");

        return restaurantModel;
    }

    public static DishResponseDto getDishResponseDto() {
        DishResponseDto dishResponseDto = new DishResponseDto();

        dishResponseDto.setName("Arroz");
        dishResponseDto.setCategoryId(getCategoryResponseDto());
        dishResponseDto.setDescription("restaurante");
        dishResponseDto.setPrice(200);
        dishResponseDto.setRestaurantId(getRestaurantResponseDto());
        dishResponseDto.setUrlImage("urlImage");
        dishResponseDto.setActive(true);

        return dishResponseDto;
    }

    public static CategoryResponseDto getCategoryResponseDto() {
        CategoryResponseDto categoryResponseDto = new CategoryResponseDto();
        categoryResponseDto.setName("categoria");
        categoryResponseDto.setDescription("descripcion");
        return categoryResponseDto;
    }


    public static RestaurantResponseDto getRestaurantResponseDto() {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setName("Subway");
        restaurantResponseDto.setAddress("Calle 5");
        restaurantResponseDto.setOwnerId(3l);
        restaurantResponseDto.setPhoneNumber("+10000");
        restaurantResponseDto.setUrlLogo("logoUrl");
        restaurantResponseDto.setNit("20000");

        return restaurantResponseDto;
    }

    public static RestaurantModel getRestaurantModelIncorrectId() {
        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setId(1L);
        restaurantModel.setName("Subway");
        restaurantModel.setAddress("Calle 5");
        restaurantModel.setOwnerId(3l);
        restaurantModel.setPhoneNumber("+10000");
        restaurantModel.setUrlLogo("logoUrl");
        restaurantModel.setNit("20000");

        return restaurantModel;
    }

    public static DishUpdateRequestDto getDishUpdateRequest(){
        DishUpdateRequestDto dishUpdateRequestDto = new DishUpdateRequestDto();

        dishUpdateRequestDto.setId(1L);
        dishUpdateRequestDto.setDescription("restaurante2");
        dishUpdateRequestDto.setPrice(300);

        return dishUpdateRequestDto;
    }

    public static DishResponseDto getDishUpdateResponseDto() {
        DishResponseDto dishResponseDto = new DishResponseDto();

        dishResponseDto.setName("Arroz");
        dishResponseDto.setCategoryId(getCategoryResponseDto());
        dishResponseDto.setDescription("restaurante2");
        dishResponseDto.setPrice(300);
        dishResponseDto.setRestaurantId(getRestaurantResponseDto());
        dishResponseDto.setUrlImage("urlImage");
        dishResponseDto.setActive(true);

        return dishResponseDto;
    }

    public static UserRequestDto getUserRequestDto(){
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setId(3L);
        return userRequestDto;
    }

}
