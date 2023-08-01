package com.pragma.powerup.infrastructure.input.rest.client;

import com.pragma.powerup.application.dto.request.TwilioRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "twilio", path = "/api/v1/twilio", url = "http://localhost:8093")
public interface ITwilioClient {
    @PostMapping("/")
    public ResponseEntity<ResponseDto> sendMessage(@RequestBody TwilioRequestDto twilioRequestDto);
}
