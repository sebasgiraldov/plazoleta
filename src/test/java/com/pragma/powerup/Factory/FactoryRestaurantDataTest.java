package com.pragma.powerup.Factory;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.request.RolRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.ResponseClientDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.domain.model.UserModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class FactoryRestaurantDataTest {

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

    public static RestaurantRequestDto getRestaurantRequestDto() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("Frisby");
        restaurantRequestDto.setAddress("Avenida Santander");
        restaurantRequestDto.setOwnerId(3L);
        restaurantRequestDto.setPhoneNumber("+573148022302");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
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

    public static RestaurantRequestDto getRestaurantWithoutName() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setAddress("Avenida Santander");
        restaurantRequestDto.setOwnerId(3L);
        restaurantRequestDto.setPhoneNumber("+573148022302");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantRequestDto getRestaurantBadPhoneNumber() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("Frisby");
        restaurantRequestDto.setAddress("Avenida Santander");
        restaurantRequestDto.setOwnerId(3L);
        restaurantRequestDto.setPhoneNumber("Telefono");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static RestaurantRequestDto getRestaurantInvalidName() {
        RestaurantRequestDto restaurantRequestDto = new RestaurantRequestDto();

        restaurantRequestDto.setName("123546");
        restaurantRequestDto.setAddress("Avenida Santander");
        restaurantRequestDto.setOwnerId(3L);
        restaurantRequestDto.setPhoneNumber("+573148022302");
        restaurantRequestDto.setUrlLogo("logoUrl");
        restaurantRequestDto.setNit("20000");

        return restaurantRequestDto;
    }

    public static Validator getValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        return validator;
    }

    public static UserRequestDto getUserRequestDto() {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setId(1L);
        userRequestDto.setName("Juan Sebastian");
        userRequestDto.setLastName("Giraldo");
        userRequestDto.setIdNumber("1193078576");
        userRequestDto.setPhone("+573148022302");
        userRequestDto.setEmail("sebasgiraldov@gmail.com");
        userRequestDto.setPassword("1234");
        userRequestDto.setRolId(getRolRquestDto());

        return userRequestDto;
    }

    public static RolRequestDto getRolRquestDto() {
        RolRequestDto rolRequestDto = new RolRequestDto();
        rolRequestDto.setName("ROLE_ADMINISTRADOR");
        rolRequestDto.setDescription("Administrador");
        return rolRequestDto;
    }

    public static ResponseClientDto getResponseClientDto() {
        ResponseClientDto responseClientDto = new ResponseClientDto();

        responseClientDto.setMessage("");
        responseClientDto.setError(false);
        responseClientDto.setData(getUserRequestDto());

        return responseClientDto;
    }

    public static ResponseEntity<ResponseClientDto> getResponseEntity() {
        ResponseClientDto responseClientDto = getResponseClientDto();
        return new ResponseEntity<>(responseClientDto, HttpStatus.FOUND);
    }

    public static RestaurantEmployeeModel getRestaurantEmployeeModel() {
        RestaurantEmployeeModel restaurantEmployeeModel = new RestaurantEmployeeModel();

        restaurantEmployeeModel.setRestaurantId(getRestaurantModel());
        restaurantEmployeeModel.setEmployeeId(getUserModel());
        restaurantEmployeeModel.setField("");

        return restaurantEmployeeModel;
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
}
