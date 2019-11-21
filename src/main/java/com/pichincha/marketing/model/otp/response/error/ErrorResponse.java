package com.pichincha.marketing.model.otp.response.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    String codigo;
    String mensaje;
    String mensajeNegocio;
}
