package com.pragma.powerup.infrastructure.input.rest.client;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "traceability", path = "/api/v1/traceability", url = "http://localhost:8096")
public interface ITraceabilityClient {
    @PostMapping("/")
    public ResponseEntity<ResponseDto> saveOrderLog(@RequestBody OrderLogRequestDto orderLogRequestDto);
}
