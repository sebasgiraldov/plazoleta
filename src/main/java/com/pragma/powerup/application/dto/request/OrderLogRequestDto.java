package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class OrderLogRequestDto {

    @NotNull(message = "Id del cliente es obligatorio")
    private Long clientId;
    @NotNull(message = "Id del pedido es obligatorio")
    private Long orderId;
    @NotNull(message = "Id del restaurante es obligatorio")
    private Long restaurantId;
    private Long employeeId;
    @NotNull(message = "La fecha es obligatoria")
    private Date date;
    @NotNull(message = "El estado es obligatorio")
    private String state;
}
