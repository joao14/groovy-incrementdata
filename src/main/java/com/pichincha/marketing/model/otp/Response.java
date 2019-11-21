package com.pichincha.marketing.model.otp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pichincha.marketing.model.otp.response.GenerarOTP51Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @JsonPropertyOrder("1")
    @JsonAlias("GenerarOTP51Response")
    @JsonProperty("GenerarOTP51Response")
    GenerarOTP51Response GenerarOTP51Response;
}
