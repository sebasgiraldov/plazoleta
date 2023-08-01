package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.DishRequestDto;
import com.pragma.powerup.application.dto.request.DishUpdateRequestDto;
import com.pragma.powerup.application.dto.request.ListPaginationRequest;
import com.pragma.powerup.application.dto.response.DishResponseDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.application.handler.IDishHandler;
import com.pragma.powerup.infrastructure.exception.CategoryNotFoundException;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.NotEnoughPrivileges;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dish")
@RequiredArgsConstructor
public class DishRestController {

    private final IDishHandler dishHandler;

    @Operation(summary = "Add a new dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Dish created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Dish validation failed BadRequest", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough privileges", content = @Content),
            @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Restaurant not found", content = @Content)
    })
    @RolesAllowed("ROLE_PROPIETARIO")
    @PostMapping("/")
    public ResponseEntity<ResponseDto> saveDish(@Valid @RequestBody DishRequestDto dishRequestDto,
                                                BindingResult bindingResult) {
        ResponseDto responseDto = new ResponseDto();

        if (bindingResult.hasErrors()) {
            return ValidationErrors(bindingResult, responseDto);
        }

        try {
            DishResponseDto dishResponseDto = dishHandler.saveDish(dishRequestDto);

            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(dishResponseDto);

        } catch (CategoryNotFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró la categoria");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (RestaurantNotFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró el restaurante");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (NotEnoughPrivileges ex) {
            responseDto.setError(true);
            responseDto.setMessage("No tienes suficientes privilegios para realizar esta accion");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/alldishes")
    public ResponseEntity<List<DishResponseDto>> getAllDishes() {
        return ResponseEntity.ok(dishHandler.getAllDishes());
    }

    @Operation(summary = "Get all restaurant dishes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All dishes listed",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = DishResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @GetMapping("/alldishes/{id}")
    public ResponseEntity<ResponseDto> getAllDishesByRestaurantId(@Valid @RequestBody ListPaginationRequest listPaginationRequest,
                                                                  @PathVariable Long id,
                                                                  BindingResult bindingResult) {
        ResponseDto responseDto = new ResponseDto();

        if (bindingResult.hasErrors()) {
            return ValidationErrors(bindingResult, responseDto);
        }

        try {
            List<DishResponseDto> dishResponseDtoList = dishHandler.getAllDishesByRestaurant(listPaginationRequest, id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(dishResponseDtoList);

        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró el restaurante");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough privileges", content = @Content)
    })
    @RolesAllowed({"ROLE_PROPIETARIO"})
    @PutMapping("/")
    public ResponseEntity<ResponseDto> updateDish(@Valid @RequestBody DishUpdateRequestDto dishUpdateRequestDto,
                                                  BindingResult bindingResult) {

        ResponseDto responseDto = new ResponseDto();

        if (bindingResult.hasErrors()) {
            return ValidationErrors(bindingResult, responseDto);
        }

        try {
            DishResponseDto dishResponseDto = dishHandler.updateDish(dishUpdateRequestDto);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(dishResponseDto);
        } catch (NotEnoughPrivileges ex) {
            responseDto.setError(true);
            responseDto.setMessage("No tienes suficientes privilegios para realizar esta accion");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("Error interno del servidor");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    @Operation(summary = "Enable or disable an existing dish")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dish updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Dish not found", content = @Content),
            @ApiResponse(responseCode = "403", description = "Not enough privileges", content = @Content)
    })
    @RolesAllowed({"ROLE_PROPIETARIO"})
    @PutMapping("/enable/{id}")
    public ResponseEntity<ResponseDto> enableOrDisableDish(@PathVariable Long id) {

        System.out.println("LLEGO A ROUTER"+id);
        ResponseDto responseDto = new ResponseDto();

        try {
            DishResponseDto dishResponseDto = dishHandler.enableDish(id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(dishResponseDto);

        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró el plato");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        } catch (NotEnoughPrivileges ex) {
            responseDto.setError(true);
            responseDto.setMessage("No tienes suficientes privilegios para realizar esta accion");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    private ResponseEntity<ResponseDto> ValidationErrors(BindingResult bindingResult, ResponseDto responseDto) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

        responseDto.setError(true);
        responseDto.setMessage("Error en las validaciones");
        responseDto.setData(errors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

}
