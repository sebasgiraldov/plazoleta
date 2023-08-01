package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DeliverOrderRequestDto {
    @NotNull(message = "El id es obligatorio")
    private Long orderId;
    @NotNull(message = "El pin no puede ser nulo")
    private Long pin;
}
