package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DeliverOrderRequestDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.OrderStateResponseDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.domain.model.OrderState;
import com.pragma.powerup.infrastructure.exception.*;
import com.pragma.powerup.infrastructure.exceptionhandler.RestaurantNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderRestController {

    private final IOrderHandler orderHandler;

    @Operation(summary = "Create an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orded created",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Restaurant not found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Dish not found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "Dish not found in the restaurant",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
            @ApiResponse(responseCode = "400", description = "The user cannot create another order",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @RolesAllowed({"ROLE_CLIENTE"})
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            OrderResponseDto orderResponseDto = orderHandler.createOrder(orderRequestDto);

            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderResponseDto);
        } catch (RestaurantNotFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Restaurante no encontrado");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (DishNotFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Plato no encontrado");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (DishNotFoundInRestaurantException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Plato no encontrado en el restaurante");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (UserCannotMakeAnOrderException ex) {
            responseDto.setError(true);
            responseDto.setMessage("El usuario no puede crear otro pedido");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "List all orders by order state")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orders listed",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
    })
    @RolesAllowed({"ROLE_EMPLEADO"})
    @GetMapping("/get/{orderState}")
    public ResponseEntity<ResponseDto> getOrderByOrderState(@PathVariable OrderState orderState) {
        ResponseDto responseDto = new ResponseDto();

        try {
            List<OrderStateResponseDto> orderStateResponseDtoList = orderHandler.getAllOrdersByOrderState(orderState);

            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderStateResponseDtoList);
        }  catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "Asign an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orded asigned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
    })
    @RolesAllowed({"ROLE_EMPLEADO"})
    @PutMapping("/asignorder/{orderId}")
    public ResponseEntity<ResponseDto> asignOrderToEmployee(@PathVariable Long orderId) {
        ResponseDto responseDto = new ResponseDto();

        try {
            OrderResponseDto orderResponseDto = orderHandler.asignAnOrder(orderId);

            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderResponseDto);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    @Operation(summary = "Notify order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Ready",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
    })
    @RolesAllowed({"ROLE_EMPLEADO"})
    @PutMapping("/notify/{orderId}")
    public ResponseEntity<ResponseDto> notifyOrder(@PathVariable Long orderId) {
        ResponseDto responseDto = new ResponseDto();

        try {
            OrderResponseDto orderResponseDto = orderHandler.notifyOrder(orderId);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderResponseDto);
        }catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Tuvimos un problema para enviar el mensaje");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    @Operation(summary = "Deliver order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order delivered",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
    })
    @RolesAllowed({"ROLE_EMPLEADO"})
    @PutMapping("/deliver")
    public ResponseEntity<ResponseDto> deliverOrder(@RequestBody DeliverOrderRequestDto deliverOrderRequestDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            OrderResponseDto orderResponseDto = orderHandler.deliverOrder(deliverOrderRequestDto.getOrderId(), deliverOrderRequestDto.getPin());
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderResponseDto);
        }catch (OrderIsNotReadyException ex) {
            responseDto.setError(true);
            responseDto.setMessage("La orden no esta lista");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (WrongPingException ex) {
            responseDto.setError(true);
            responseDto.setMessage("El pin es incorrecto");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "Cancel order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order Cancel",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = OrderResponseDto.class)))),
    })
    @RolesAllowed({"ROLE_CLIENTE"})
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<ResponseDto> cancelOrder(@PathVariable Long orderId) {
        ResponseDto responseDto = new ResponseDto();

        try {
            OrderResponseDto orderResponseDto = orderHandler.cancelOrder(orderId);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderResponseDto);
        }catch (OrderInPreparationException ex) {
            responseDto.setError(true);
            responseDto.setMessage("Lo sentimos, tu pedido ya está en preparación y no puede cancelarse");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
