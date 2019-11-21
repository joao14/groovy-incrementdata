package com.pichincha.marketing.model.otp.request.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Otp {

    @JsonPropertyOrder("1")
    @JsonAlias("otp")
    @JsonProperty("otp")
    String otp;

    @JsonPropertyOrder("2")
    @JsonAlias("reintentos")
    @JsonProperty("reintentos")
    String reintentos;
}
