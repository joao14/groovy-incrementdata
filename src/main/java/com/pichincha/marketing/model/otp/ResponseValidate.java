package com.pichincha.marketing.model.otp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pichincha.marketing.model.otp.response.EvaluarOTP52Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseValidate {

    @JsonPropertyOrder("1")
    @JsonAlias("EvaluarOTP52Response")
    @JsonProperty("EvaluarOTP52Response")
    EvaluarOTP52Response EvaluarOTP52Response;
}
