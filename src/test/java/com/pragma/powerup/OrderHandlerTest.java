package com.pragma.powerup;

import com.pragma.powerup.Factory.FactoryDishDataTest;
import com.pragma.powerup.Factory.FactoryOrderDataTest;
import com.pragma.powerup.Factory.FactoryRestaurantDataTest;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.OrderStateResponseDto;
import com.pragma.powerup.application.dto.response.ResponseClientDto;
import com.pragma.powerup.application.handler.IOrderDishHandler;
import com.pragma.powerup.application.handler.impl.JwtHandler;
import com.pragma.powerup.application.handler.impl.OrderHandler;
import com.pragma.powerup.application.mapper.IOrderDishResponseMapper;
import com.pragma.powerup.application.mapper.IOrderResponseMapper;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.domain.api.*;
import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.infrastructure.configuration.FeignClientInterceptorImp;
import com.pragma.powerup.infrastructure.exception.DishNotFoundInRestaurantException;
import com.pragma.powerup.infrastructure.input.rest.client.IUserClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class OrderHandlerTest {
    @InjectMocks
    OrderHandler orderHandler;

    @Mock
    IRestaurantServicePort restaurantServicePort;
    @Mock
    JwtHandler jwtHandler;
    @Mock
    IUserClient userClient;
    @Mock
    IOrderServicePort orderServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    IOrderDishHandler orderDishHandler;
    @Mock
    IOrderResponseMapper orderResponseMapper;
    @Mock
    IOrderDishServicePort orderDishServicePort;
    @Mock
    IOrderDishResponseMapper orderDishResponseMapper;
    @Mock
    IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    @Mock
    IDishServicePort dishServicePort;





    @Test
    void mustListAllOrdersByState() {
        OrderState orderState = OrderState.PENDIENTE;
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        RestaurantEmployeeModel restaurantEmployeeModel = FactoryRestaurantDataTest.getRestaurantEmployeeModel();
        RestaurantModel restaurantModel = FactoryOrderDataTest.getRestaurantModel();
        OrderDishModel orderDishModel = FactoryOrderDataTest.getOrderDishModel();
        OrderModel orderModel = FactoryOrderDataTest.getOrderModel();
        OrderStateResponseDto orderStateResponseDto = FactoryOrderDataTest.getOrderStateResponseDto();
        List<OrderStateResponseDto> orderStateResponseDtoList = new ArrayList<>();
        orderStateResponseDtoList.add(orderStateResponseDto);

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(restaurantEmployeeServicePort.getRestaurantByEmployeeId(anyLong())).thenReturn(restaurantEmployeeModel);
            when(restaurantServicePort.getAllRestaurants()).thenReturn(List.of(restaurantModel));
            when(orderDishServicePort.getAllOrderDish()).thenReturn(List.of(orderDishModel));
            when(orderServicePort.getAllOrdersByOrderState(orderState, 1L)).thenReturn(List.of(orderModel));
            when(orderResponseMapper.toReponseList(any(), any(), any())).thenReturn(List.of(orderStateResponseDto));

            Assertions.assertEquals(orderStateResponseDtoList, orderHandler.getAllOrdersByOrderState(orderState));
        }
    }
}