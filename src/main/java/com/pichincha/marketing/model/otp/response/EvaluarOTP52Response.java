package com.pichincha.marketing.model.otp.response;

import com.pichincha.marketing.model.otp.response.body.BodyValidateOut;
import com.pichincha.marketing.model.otp.response.error.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EvaluarOTP52Response {

    BodyValidateOut bodyOut;

    ErrorResponse error;
}
