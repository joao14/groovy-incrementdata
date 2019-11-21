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


public class TransaccionValidate {

    @JsonPropertyOrder("1")
    @JsonAlias("sesion")
    @JsonProperty("sesion")
    String sesion;

    @JsonPropertyOrder("2")
    @JsonAlias("tipoTransaccion")
    @JsonProperty("tipoTransaccion")
    String tipoTransaccion;

}
