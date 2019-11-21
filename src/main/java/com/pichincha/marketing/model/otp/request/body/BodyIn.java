package com.pichincha.marketing.model.otp.request.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BodyIn {

    @JsonPropertyOrder("1")
    @JsonAlias("ordenante")
    @JsonProperty("ordenante")
    Ordenante ordenante;

    @JsonPropertyOrder("2")
    @JsonAlias("transaccion")
    @JsonProperty("transaccion")
    Transaccion transaccion;



}