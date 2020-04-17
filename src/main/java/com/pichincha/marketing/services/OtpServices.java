package com.pichincha.marketing.services;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.exception.ApiRequestException;
import com.pichincha.marketing.model.InSolicitudRequest;
import com.pichincha.marketing.util.ApiResponse;
import com.pichincha.marketing.util.UtilResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class OtpServices {

    private static final String LOGGER_RESPONSE_FORMAT = "004-RES";
    private static final String LOGGER_REQUEST_FORMAT = "004-REQ";
    private static final Logger logger = LoggerFactory.getLogger(OtpServices.class);
    @Autowired
    Environment env;

    UtilResponse responseUtil = new UtilResponse();

    RestTemplate restTemplate = new RestTemplate();


    /**
     * Funcion que permite generar un OTP
     *
     * @param inCliente
     * @return
     */
    public ResponseEntity<ApiResponse> generateOtp(InCliente inCliente, HttpServletRequest req) {
        logger.info("Se realizará el proceso para generar el código Otp al cliente", LOGGER_REQUEST_FORMAT);
        String method = "generateOtp";
        ApiResponse apiResponse = null;
        ResponseEntity<ApiResponse> response = null;
        try {
            //Validamos el cliente
            if (inCliente != null) {
                String url = env.getRequiredProperty("url.get.otp");
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                //Json para envío de HTTP
                String requestJson = "{\"telefono\": " + "\"" + inCliente.getClieCelular() + "\"" + ",\"identificacion\": " + "\"" + responseUtil.validateIdentificacion(inCliente.getClieIdentificacion()) + "\"" + "}";
                logger.info("JSON generate OTP => " + requestJson, method, LOGGER_REQUEST_FORMAT);
                HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
                try {
                    response = restTemplate.exchange(url, HttpMethod.PUT, entity, new ParameterizedTypeReference<ApiResponse>() {
                    });
                    if (response.getStatusCodeValue() == 200) {
                        apiResponse = new ApiResponse(
                                "El Otp fue enviado correctamente al cliente " + inCliente.getCliePrimnomb() + " " + inCliente.getCliePrimapel(),
                                String.valueOf(HttpStatus.OK.value()),
                                HttpStatus.OK,
                                new Date(),
                                null);
                        logger.info("Se ha generado correctamente el otp para el cliente   \t" + inCliente.getClieIdentificacion() + " =>"
                                + response.getBody(), method, LOGGER_RESPONSE_FORMAT);
                    } else {
                        apiResponse = new ApiResponse(
                                "El Otp no fue enviado correctamente al cliente " + inCliente.getCliePrimnomb() + " " + inCliente.getCliePrimapel(),
                                String.valueOf(HttpStatus.CONFLICT.value()),
                                HttpStatus.CONFLICT,
                                new Date(),
                                null);
                        logger.info("No se ha generado correctamente el otp para el cliente   \t" + inCliente.getClieIdentificacion() + " =>"
                                + response.getBody(), method, LOGGER_RESPONSE_FORMAT);

                    }

                } catch (Exception exc) {

                    logger.error("Error en el método  :  \t" + method +
                            "\t" + exc.getMessage(), method, LOGGER_RESPONSE_FORMAT);
                    throw new ApiRequestException("No se pudo generar el Otp para cliente");

                }
            }
        } catch (ApiRequestException e) {
            logger.error("Uups, surgio un error al momento de generar el otp para el cliente \t " + inCliente.getClieIdentificacion(), e.toString(), LOGGER_RESPONSE_FORMAT);
            throw new ApiRequestException("No se pudo generar el otp");
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


    /**
     * Funcion que permite validar el codigo OTP ingresado por el cliente
     *
     * @param inSolicitudRequest
     * @return
     */
    public ResponseEntity<ApiResponse> validateOtp(InCliente inCliente, InSolicitudRequest inSolicitudRequest, HttpServletRequest req) {
        logger.info("Se realizará el proceso pra validar el código Otp del cliente =>" + inCliente.getClieIdentificacion(), LOGGER_REQUEST_FORMAT);
        String method = "validateOtp";
        ApiResponse apiResponse = null;
        ResponseEntity<ApiResponse> response = null;
        try {
            //Validamos el cliente
            if (inCliente != null) {
                String url = env.getRequiredProperty("url.validate.otp");
                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                //Json para envío de HTTP
                String requestJson = "{\"identificacion\": " + "\"" + responseUtil.validateIdentificacion(inCliente.getClieIdentificacion()) + "\"" + ",\"otp\": " + "\"" + inSolicitudRequest.getOtp()+ "\"" + "}";
                logger.info("JSON validar OTP => " + requestJson, method, LOGGER_REQUEST_FORMAT);
                HttpEntity<String> entity = new HttpEntity<String>(requestJson, httpHeaders);
                try {
                    response = restTemplate.exchange(url, HttpMethod.POST, entity, new ParameterizedTypeReference<ApiResponse>() {
                    });
                    if (response.getStatusCodeValue() == 200) {
                        apiResponse = new ApiResponse(
                                "El Otp fue validado correctamente al cliente " + inCliente.getCliePrimnomb() + " " + inCliente.getCliePrimapel(),
                                String.valueOf(HttpStatus.OK.value()),
                                HttpStatus.OK, new Date(),
                                null);
                        logger.info("Se ha validado correctamente el otp para el cliente   \t" + inCliente.getClieIdentificacion() + " =>"
                                + response.getBody(), method, LOGGER_RESPONSE_FORMAT);
                    } else {
                        apiResponse = new ApiResponse(
                                "El Otp no pudo ser validado correctamente al cliente " + inCliente.getCliePrimnomb() + " " + inCliente.getCliePrimapel(),
                                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                                HttpStatus.BAD_REQUEST, new Date(),
                                null);
                        logger.error("Error al validar el otp para el cliente   \t" + inCliente.getClieIdentificacion() + " =>"
                                + response.getBody(), method, LOGGER_RESPONSE_FORMAT);
                    }

                } catch (Exception exc) {
                    logger.error("Error en el método  :  \t" + method +"\t" + exc.getMessage(), method, LOGGER_RESPONSE_FORMAT);
                    throw new ApiRequestException("No se pudo validar el Otp para cliente");

                }
            }
        } catch (ApiRequestException e) {
            logger.error("Uups, surgio un error al momento de validar el otp para el cliente \t " + inCliente.getClieIdentificacion(), method, LOGGER_RESPONSE_FORMAT);
            throw new ApiRequestException("No se pudo validar el otp");
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


}
