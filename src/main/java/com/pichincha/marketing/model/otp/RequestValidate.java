package com.pichincha.marketing.model.otp;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pichincha.marketing.model.otp.request.EvaluarOTP52;
import com.pichincha.marketing.model.otp.request.Generarotp51;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        setterVisibility = JsonAutoDetect.Visibility.NONE)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestValidate {

    @JsonPropertyOrder("1")
    @JsonAlias("EvaluarOTP52")
    @JsonProperty("EvaluarOTP52")
    EvaluarOTP52 EvaluarOTP52;
}
