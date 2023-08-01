package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.application.dto.response.RestaurantEmployeeResponseDto;
import com.pragma.powerup.application.handler.IRestaurantEmployeeHandler;
import com.pragma.powerup.infrastructure.exception.NotEnoughPrivileges;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/restaurantemployee")
@RequiredArgsConstructor
public class RestaurantEmployeeRestController {

    private final IRestaurantEmployeeHandler restaurantEmployeeHandler;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> saveRestaurantEmployee(@RequestBody RestaurantEmployeeRequestDto restaurantEmployeeRequestDto) {
        ResponseDto responseDto = new ResponseDto();

        try {
            RestaurantEmployeeResponseDto restaurantEmployeeResponseDto = restaurantEmployeeHandler.saveRestaurantEmployee(restaurantEmployeeRequestDto);

            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(restaurantEmployeeResponseDto);
        } catch (NotEnoughPrivileges ex) {
            responseDto.setError(true);
            responseDto.setMessage("El usuario no es dueño del restaurante");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


}
