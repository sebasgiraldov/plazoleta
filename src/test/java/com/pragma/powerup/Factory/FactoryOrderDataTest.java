package com.pragma.powerup.Factory;

import com.pragma.powerup.application.dto.request.OrderDishRequestDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.OrderStateResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FactoryOrderDataTest {

    public static OrderRequestDto getOrderRequestDto() {
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        List<OrderDishRequestDto> orderDishRequestDtoList = new ArrayList<>();
        orderDishRequestDtoList.add(getOrderDishRequestDto());
        orderDishRequestDtoList.add(getOrderDishRequestDto());

        orderRequestDto.setRestaurantId(1L);
        orderRequestDto.setOrders(orderDishRequestDtoList);

        return orderRequestDto;
    }

    public static OrderDishRequestDto getOrderDishRequestDto(){
        OrderDishRequestDto orderDishRequestDto = new OrderDishRequestDto();
        orderDishRequestDto.setDishId(1L);
        orderDishRequestDto.setAmount(50);

        return orderDishRequestDto;
    }

    public static RestaurantModel getRestaurantModel(){
        RestaurantModel restaurantModel = new RestaurantModel();
        restaurantModel.setName("Frisby");
        restaurantModel.setAddress("Avenida Santander");
        restaurantModel.setOwnerId(3L);
        restaurantModel.setPhoneNumber("+573148022302");
        restaurantModel.setUrlLogo("google.com");
        restaurantModel.setNit("123478525");

        return restaurantModel;
    }

    public static UserModel getUserModel() {
        UserModel userModel = new UserModel();

        userModel.setId(1L);
        userModel.setName("Juan Sebastian");
        userModel.setLastName("Giraldo");
        userModel.setIdNumber("1193078576");
        userModel.setPhone("+573148022302");
        userModel.setEmail("sebasgiraldov@gmail.com");
        userModel.setPassword("1234");
        userModel.setRolId(null);

        return userModel;
    }

    public static RolModel getRolModelClient() {
        RolModel rolModel = new RolModel();
        rolModel.setId(4L);
        rolModel.setName("ROLE_CLIENTE");
        rolModel.setDescription("Cliente");
        return rolModel;
    }

    public static OrderModel getOrderModel() {
        OrderModel orderModel = new OrderModel();
        orderModel.setId(1L);
        orderModel.setClientId(getUserModel());
        orderModel.setDate(new Date());
        orderModel.setOrderState(OrderState.PENDIENTE);
        orderModel.setChefId(null);
        orderModel.setRestaurantId(getRestaurantModel());
        return orderModel;
    }

    public static OrderResponseDto getOrderResponseDto() {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        List<OrderDishResponseDto> orderDishResponseDtoList = new ArrayList<>();
        orderDishResponseDtoList.add(getOrderDishResponseDto());
        orderDishResponseDtoList.add(getOrderDishResponseDto());

        orderResponseDto.setOrderId(1L);
        orderResponseDto.setDate(new Date());
        orderResponseDto.setOrderState(OrderState.PENDIENTE);
        orderResponseDto.setRestaurantId(getRestaurantResponseDto());
        orderResponseDto.setOrders(orderDishResponseDtoList);

        return orderResponseDto;
    }

    public static RestaurantResponseDto getRestaurantResponseDto() {
        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setName("Frisby");
        restaurantResponseDto.setAddress("Avenida Santander");
        restaurantResponseDto.setOwnerId(3L);
        restaurantResponseDto.setPhoneNumber("+573148022302");
        restaurantResponseDto.setUrlLogo("logoUrl");
        restaurantResponseDto.setNit("20000");

        return restaurantResponseDto;
    }

    public static OrderDishResponseDto getOrderDishResponseDto() {
        OrderDishResponseDto orderDishResponseDto = new OrderDishResponseDto();


        return orderDishResponseDto;
    }

    public static OrderDishModel getOrderDishModel() {
        OrderDishModel orderDishModel = new OrderDishModel();

        orderDishModel.setId(1L);
        orderDishModel.setAmount(2);

        return orderDishModel;
    }

    public static OrderDishResponseDto orderDishResponseDto() {
        OrderDishResponseDto orderDishResponseDto = new OrderDishResponseDto();
        return orderDishResponseDto;
    }

    public static OrderStateResponseDto getOrderStateResponseDto() {
        OrderStateResponseDto orderStateResponseDto = new OrderStateResponseDto();

        orderStateResponseDto.setOrderState(OrderState.PENDIENTE);
        orderStateResponseDto.setOrderId(1L);
        orderStateResponseDto.setDate(new Date());
        orderStateResponseDto.setOrderDishIds(List.of(1L, 2L));

        return orderStateResponseDto;
    }


}
