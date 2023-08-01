package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.*;
import com.pragma.powerup.application.dto.response.*;
import com.pragma.powerup.application.handler.IOrderDishHandler;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderDishResponseMapper;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.IOrderResponseMapper;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.domain.api.*;
import com.pragma.powerup.domain.model.*;
import com.pragma.powerup.infrastructure.configuration.FeignClientInterceptorImp;
import com.pragma.powerup.infrastructure.exception.*;
import com.pragma.powerup.infrastructure.input.rest.client.ITraceabilityClient;
import com.pragma.powerup.infrastructure.input.rest.client.ITwilioClient;
import com.pragma.powerup.infrastructure.input.rest.client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;
    private final IOrderDishResponseMapper orderDishResponseMapper;
    private final IOrderDishServicePort orderDishServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderDishHandler orderDishHandler;
    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    private final JwtHandler jwtHandler;
    private final IUserClient userClient;
    private final IUserRequestMapper userRequestMapper;
    private final IDishServicePort dishServicePort;
    private final ITwilioClient twilioClient;
    private final ITraceabilityClient traceabilityClient;

    Date fechaActual = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    String fechaFormateada = dateFormat.format(fechaActual);


    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(orderRequestDto.getRestaurantId());
        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String[] splited = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();
        List<OrderState> orderStateList = Arrays.asList(OrderState.EN_PREPARACION, OrderState.PENDIENTE, OrderState.LISTO);

        orderServicePort.getAllOrdersByUserIdOrderStateIn(userRequestDto.getId(), orderStateList);

        OrderModel orderModel = new OrderModel();
        orderModel.setClientId(userRequestMapper.toUser(userRequestDto));
        orderModel.setDate(new Date());
        orderModel.setOrderState(OrderState.PENDIENTE);
        orderModel.setChefId(null);
        orderModel.setRestaurantId(restaurantModel);

        OrderModel orderModelResponse = orderServicePort.createOrder(orderModel);

        OrderLogRequestDto orderLogRequestDto = new OrderLogRequestDto();
        orderLogRequestDto.setOrderId(orderModelResponse.getId());
        orderLogRequestDto.setClientId(orderModelResponse.getClientId().getId());
        orderLogRequestDto.setState(orderModelResponse.getOrderState().name());
        orderLogRequestDto.setDate(new Date());
        orderLogRequestDto.setRestaurantId(orderModelResponse.getRestaurantId().getId());
        ResponseDto responseDto = traceabilityClient.saveOrderLog(orderLogRequestDto).getBody();
        List<OrderDishRequestDto> orderDishRequestDtoList = orderRequestDto.getOrders();

        List<OrderDishResponseDto> orderDishResponseDtoList =
                orderDishRequestDtoList.stream()
                        .map(orderDish -> {

                            if (!Objects.equals(dishServicePort.getDish(orderDish.getDishId()).getRestaurantId().getId(), restaurantModel.getId())) {
                                throw new DishNotFoundInRestaurantException();
                            }

                            return orderDishHandler.createOrderDish(orderDish, orderModelResponse.getId());
                        }).collect(Collectors.toList());

        return orderResponseMapper.toResponse(orderModelResponse, orderDishResponseDtoList);
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderStateResponseDto> getAllOrdersByOrderState(OrderState orderState) {
        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String splited[] = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();

        RestaurantEmployeeModel restaurantEmployeeModel = restaurantEmployeeServicePort.getRestaurantByEmployeeId(userRequestDto.getId());

        return orderResponseMapper.toReponseList(orderServicePort.getAllOrdersByOrderState(orderState, restaurantEmployeeModel.getRestaurantId().getId()), restaurantServicePort.getAllRestaurants(), orderDishServicePort.getAllOrderDish());
    }

    @Override
    public OrderResponseDto asignAnOrder(Long orderId) {
        OrderModel orderModel = orderServicePort.getOrder(orderId);

        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String splited[] = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();

        orderModel.setChefId(userRequestMapper.toUser(userRequestDto));
        orderModel.setOrderState(OrderState.EN_PREPARACION);

        OrderResponseDto orderResponseDto = updateOrder(orderId, orderModel);
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto notifyOrder(Long orderId) {
        OrderModel orderModel = orderServicePort.getOrder(orderId);
        orderModel.setOrderState(OrderState.LISTO);
        TwilioRequestDto twilioRequestDto = new TwilioRequestDto();
        twilioRequestDto.setMessage(String.valueOf(orderModel.getId()*1110));
        UserRequestDto userRequestDto = userClient.getUserById(orderModel.getClientId().getId()).getBody().getData();
        twilioRequestDto.setNumber(userRequestDto.getPhone());
        ResponseDto responseDto = twilioClient.sendMessage(twilioRequestDto).getBody();
        if(responseDto.isError()){
            throw new NoDataFoundException();
        }
        OrderResponseDto orderResponseDto = updateOrder(orderId, orderModel);
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto deliverOrder(Long orderId, Long pin) {
        OrderModel orderModel = orderServicePort.getOrder(orderId);
        if(orderModel.getOrderState().equals(OrderState.LISTO)){
            if (pin == orderId*1110){
                orderModel.setOrderState(OrderState.ENTREGADO);
            }else{
                throw new WrongPingException();
            }
        } else{
            throw new OrderIsNotReadyException();
        }

        OrderResponseDto orderResponseDto = updateOrder(orderId, orderModel);
        return orderResponseDto;
    }

    @Override
    public OrderResponseDto cancelOrder(Long orderId) {
        OrderModel orderModel = orderServicePort.getOrder(orderId);
        if(!orderModel.getOrderState().equals(OrderState.PENDIENTE)){
            throw new OrderInPreparationException();
        }
        orderModel.setOrderState(OrderState.CANCELADO);
        OrderResponseDto orderResponseDto = updateOrder(orderId, orderModel);
        return orderResponseDto;
    }

    public OrderResponseDto updateOrder(Long orderId, OrderModel orderModel){
        OrderModel orderModelResponse = orderServicePort.createOrder(orderModel);
        OrderLogRequestDto orderLogRequestDto = new OrderLogRequestDto();
        orderLogRequestDto.setOrderId(orderModel.getId());
        orderLogRequestDto.setClientId(orderModel.getClientId().getId());
        orderLogRequestDto.setState(orderModel.getOrderState().name());
        orderLogRequestDto.setDate(new Date());
        orderLogRequestDto.setRestaurantId(orderModel.getRestaurantId().getId());
        orderLogRequestDto.setEmployeeId(orderModel.getChefId().getId());
        traceabilityClient.saveOrderLog(orderLogRequestDto);
        List<OrderDishModel> orderDishModelList = orderDishServicePort.getAllOrderDishByOrder(orderId);

        List<OrderDishResponseDto> orderDishResponseDtoList = orderDishModelList.stream().map(orderDishResponseMapper::toResponse).collect(Collectors.toList());

        return orderResponseMapper.toResponse(orderModelResponse, orderDishResponseDtoList);
    }

}
