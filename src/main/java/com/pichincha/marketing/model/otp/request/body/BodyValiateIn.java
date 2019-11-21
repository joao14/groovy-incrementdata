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
public class BodyValiateIn {

    @JsonPropertyOrder("1")
    @JsonAlias("otp")
    @JsonProperty("otp")
    Otp otp;

    @JsonPropertyOrder("2")
    @JsonAlias("ordenante")
    @JsonProperty("ordenante")
    Ordenante ordenante;

    @JsonPropertyOrder("3")
    @JsonAlias("transaccion")
    @JsonProperty("transaccion")
    TransaccionValidate transaccion;
}
