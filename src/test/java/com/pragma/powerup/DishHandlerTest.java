package com.pragma.powerup;
import com.pragma.powerup.Factory.FactoryDishDataTest;
import com.pragma.powerup.Factory.FactoryRestaurantDataTest;
import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.request.ListPaginationRequest;
import com.pragma.powerup.application.dto.response.CategoryResponseDto;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.ResponseClientDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.application.handler.impl.DishHandler;
import com.pragma.powerup.application.handler.impl.JwtHandler;
import com.pragma.powerup.application.mapper.ICategoryResponseMapper;
import com.pragma.powerup.application.mapper.IDishRequestMapper;
import com.pragma.powerup.application.mapper.IDishResponseMapper;
import com.pragma.powerup.application.mapper.IRestaurantResponseMapper;
import com.pragma.powerup.domain.api.ICategoryServicePort;
import com.pragma.powerup.domain.api.IDishServicePort;
import com.pragma.powerup.domain.api.IRestaurantServicePort;
import com.pragma.powerup.domain.model.CategoryModel;
import com.pragma.powerup.domain.model.DishModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infrastructure.configuration.FeignClientInterceptorImp;
import com.pragma.powerup.infrastructure.exception.NotEnoughPrivileges;
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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DishHandlerTest {
    @InjectMocks
    DishHandler dishHandler;
    @Mock
    IDishServicePort dishServicePort;
    @Mock
    IDishRequestMapper dishRequestMapper;
    @Mock
    IDishResponseMapper dishResponseMapper;
    @Mock
    ICategoryServicePort categoryServicePort;
    @Mock
    ICategoryResponseMapper categoryResponseMapper;
    @Mock
    IRestaurantServicePort restaurantServicePort;
    @Mock
    IRestaurantResponseMapper restaurantResponseMapper;
    @Mock
    IUserClient userClient;
    @Mock
    JwtHandler jwtHandler;

    @Test
    void mustSaveADish() {
        DishRequestDto dishRequestDto = FactoryDishDataTest.getDishRequestDto();
        DishModel dishModel = FactoryDishDataTest.getDishModle();
        DishResponseDto dishResponseDto = FactoryDishDataTest.getDishResponseDto();
        CategoryModel categoryModel = FactoryDishDataTest.getCategoryModel();
        RestaurantModel restaurantModel = FactoryDishDataTest.getRestaurantModel();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        CategoryResponseDto categoryResponseDto = FactoryDishDataTest.getCategoryResponseDto();
        RestaurantResponseDto restaurantResponseDto = FactoryDishDataTest.getRestaurantResponseDto();

        when(userClient.getUserById(any())).thenReturn(response);
        when(categoryServicePort.getCategory(any())).thenReturn(categoryModel);
        when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModel);
        when(dishRequestMapper.toDish(dishRequestDto)).thenReturn(dishModel);
        when(categoryResponseMapper.toResponse(any())).thenReturn(categoryResponseDto);
        when(restaurantResponseMapper.toResponse(any())).thenReturn(restaurantResponseDto);
        when(dishResponseMapper.toResponse(any(), any(), any())).thenReturn(dishResponseDto);

        Assertions.assertEquals(dishResponseDto, dishHandler.saveDish(dishRequestDto));

        verify(dishServicePort).saveDish(any(DishModel.class));
    }

    @Test
    void throwNotEnoughPrivilegesWhereGetUserIsNotEqualsToOwnerId() {
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        RestaurantModel restaurantModelIncorrectId = FactoryDishDataTest.getRestaurantModelIncorrectId();
        DishRequestDto dishRequestDto = FactoryDishDataTest.getDishRequestDto();

        when(userClient.getUserById(any())).thenReturn(response);
        when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModelIncorrectId);

        Assertions.assertThrows(
                NotEnoughPrivileges.class,
                () -> dishHandler.saveDish(dishRequestDto)
        );
    }

    @Test
    void mustUpdateADish() {
        DishModel oldDish = FactoryDishDataTest.getDishModle();
        DishModel newDish = FactoryDishDataTest.getDishModel2();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        CategoryResponseDto categoryResponseDto = FactoryDishDataTest.getCategoryResponseDto();
        RestaurantResponseDto restaurantResponseDto = FactoryDishDataTest.getRestaurantResponseDto();
        DishResponseDto dishResponseDto = FactoryDishDataTest.getDishUpdateResponseDto();
        DishUpdateRequestDto dishUpdateRequestDto = FactoryDishDataTest.getDishUpdateRequest();
        RestaurantModel restaurantModel = FactoryDishDataTest.getRestaurantModel();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(dishServicePort.getDish(any())).thenReturn(oldDish);
            when(restaurantServicePort.getRestaurant(any())).thenReturn(newDish.getRestaurantId());
            when(dishRequestMapper.toDish(any(DishUpdateRequestDto.class))).thenReturn(newDish);
            when(categoryResponseMapper.toResponse(any())).thenReturn(categoryResponseDto);
            when(restaurantResponseMapper.toResponse(any())).thenReturn(restaurantResponseDto);
            when(dishResponseMapper.toResponse(any(), any(), any())).thenReturn(dishResponseDto);

            Assertions.assertNotEquals(restaurantModel.getOwnerId(), 2L);
            Assertions.assertEquals(dishResponseDto, dishHandler.updateDish(dishUpdateRequestDto));

            verify(dishServicePort).updateDish(any(DishModel.class));
        }
    }

    @Test
    void throwNullPointerExceptionWhereUserRequestIsNull() {
        DishUpdateRequestDto dishUpdateRequestDto = FactoryDishDataTest.getDishUpdateRequest();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(userClient.getUserByEmail(any())).thenReturn(null);

            Assertions.assertThrows(
                    NullPointerException.class,
                    () -> dishHandler.updateDish(dishUpdateRequestDto)
            );
        }
    }

    @Test
    void throwNoEnoughPrivilegesWhereUserIdIsNotTheOwnerId() {
        DishModel oldDish = FactoryDishDataTest.getDishModle();
        DishModel newDish = FactoryDishDataTest.getDishModel2();
        ResponseEntity<ResponseClientDto> response = FactoryRestaurantDataTest.getResponseEntity();
        DishUpdateRequestDto dishUpdateRequestDto = FactoryDishDataTest.getDishUpdateRequest();
        RestaurantModel restaurantModel = FactoryDishDataTest.getRestaurantModel2();

        try (MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)) {
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            when(userClient.getUserByEmail(any())).thenReturn(response);
            when(jwtHandler.extractUserName(any())).thenReturn("email@gmail.com");
            when(dishServicePort.getDish(any())).thenReturn(oldDish);
            when(restaurantServicePort.getRestaurant(any())).thenReturn(newDish.getRestaurantId());
            when(dishRequestMapper.toDish(any(DishUpdateRequestDto.class))).thenReturn(newDish);
            when(restaurantServicePort.getRestaurant(any())).thenReturn(restaurantModel);

            Assertions.assertThrows(
                    NotEnoughPrivileges.class,
                    () -> dishHandler.updateDish(dishUpdateRequestDto)
            );

        }
    }


}