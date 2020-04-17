package com.pichincha.marketing.controller;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.exception.ApiRequestException;
import com.pichincha.marketing.model.InSolicitudRequest;
import com.pichincha.marketing.model.otp.Response;
import com.pichincha.marketing.model.otp.ResponseValidate;
import com.pichincha.marketing.repository.IClienteDao;
import com.pichincha.marketing.services.*;
import com.pichincha.marketing.util.ApiResponse;
import com.pichincha.marketing.util.UtilResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class OtpController {

    private static final String LOGGER_RESPONSE_FORMAT = "001-RES";
    private static final String LOGGER_REQUEST_FORMAT = "001-REQ";
    private static final Logger logger = LoggerFactory.getLogger(OtpController.class);
    UtilResponse responseUtil = new UtilResponse();

    @Autowired
    OtpServices otpServices;
    @Autowired
    SoliincrService soliincrService;
    @Autowired
    private IClienteDao iclienteDao;
    @Autowired
    private RecaptchaService recaptchaService;
    @Autowired
    private LogsConvService logsConvService;



    @PostMapping(value = "/get/otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getOtp(@RequestParam(value = "hash") String hash, HttpServletRequest req,
                                         HttpServletResponse respo) {

        logger.info("El cliente con el hash  : " + hash + " ha enviado una solicitud para generar OTP, con la IP \t"
                + req.getRemoteAddr(), LOGGER_REQUEST_FORMAT);
        ApiResponse apiResponse = null;

        try {
            if (!recaptchaService.valid(req, respo)) {
                throw new ApiRequestException("La Captcha no ha podido ser validado :(");
            }
            logger.info("1.- Consultando datos del cliente",LOGGER_REQUEST_FORMAT);
            InCliente cliente = iclienteDao.findByCliente(hash);
            //Te actualiza la activacion de pantalla
            logger.info("2.- Actualizando el estado de conversión de caidas de la pantalla",LOGGER_REQUEST_FORMAT);
            logsConvService.updateLogsConversion(cliente, "2");
            //Genera el otp
            logger.info("3.- Se esta generando otp para el cliente ",LOGGER_REQUEST_FORMAT);
            ResponseEntity<ApiResponse> response = otpServices.generateOtp(cliente,req);
            if(response.getStatusCodeValue()==200){
                logger.error("El cliente  : " + cliente.getClieIdentificacion() + " ha recibido la otp  ",
                        LOGGER_RESPONSE_FORMAT);
                apiResponse = new ApiResponse(
                        response.getBody().getMessage(),
                        String.valueOf(response.getBody().getStatus().value()), response.getBody().getStatus(), new Date(), null);

            }else{
                logger.info("El cliente " + cliente.getClieIdentificacion() + " no ha recibido la otp  ",
                        LOGGER_RESPONSE_FORMAT);
                apiResponse = new ApiResponse(response.getBody().getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.BAD_REQUEST, new Date(), null);
            }

        } catch (ApiRequestException e) {
            logger.error(" Error al momento de consultar la informacion del cliente en el envio de otp : " + e
                    + " \t en el metodo getOtp");
            apiResponse = new ApiResponse(e.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST, new Date(), null);
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());

    }


    @PostMapping(value = "/validate/otp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> validateOtp(@RequestBody InSolicitudRequest inSolicitudRequest,
                                              HttpServletRequest req, HttpServletResponse respo) {
        logger.info("Validando el OTP ingresado del cliente  : " + inSolicitudRequest.getHash()
                + " desde la siguiente IP : " + req.getRemoteAddr(), LOGGER_REQUEST_FORMAT);
        ApiResponse apiResponse = null;
        try {
            if (!recaptchaService.valid(req, respo)) {
                throw new ApiRequestException("La Captcha no ha podido ser validado :(");
            }
            logger.info("1.- Validando la informacion del cliente dentro de la base de campañas" + inSolicitudRequest.getHash(),LOGGER_REQUEST_FORMAT);
            InCliente cliente = iclienteDao.findByCliente(responseUtil.validandoHash(inSolicitudRequest.getHash()));
            logger.info("2.- Validando el otp ingresadeo por el cliente" + inSolicitudRequest.getHash(),LOGGER_REQUEST_FORMAT);
            ResponseEntity<ApiResponse> response = otpServices.validateOtp(cliente,inSolicitudRequest,req);
            logger.info("3.- Actualizando el estado de la transacción de otp" + inSolicitudRequest.getHash(),LOGGER_REQUEST_FORMAT);
            logsConvService.updateLogsConversion(cliente, "3");
            if(response.getStatusCodeValue()==200){
                logger.error("El cliente  : " + cliente.getClieIdentificacion() + " ha validado la otp correctamente  ",
                        LOGGER_RESPONSE_FORMAT);
                logger.info("4.- Procesando el incremento para la gestión posterior" + inSolicitudRequest.getHash(),LOGGER_REQUEST_FORMAT);
                // Valida el monto enviado para aumentar
                if ((Integer.valueOf(inSolicitudRequest.getMonto()) >= (Integer.valueOf(cliente.getClieMontact()) + 5))
                        && (Integer.valueOf(inSolicitudRequest.getMonto()) <= (Integer.valueOf(cliente.getClieMontmaxi())))) {
                    logger.info(
                            "El cliente  : " + cliente.getClieIdentificacion() + " ha ingresado un otp correcto: "
                                    + inSolicitudRequest.getOtp() + " \t en el método validateOtp",
                            LOGGER_RESPONSE_FORMAT);
                    ResponseEntity<ApiResponse> respIncr = soliincrService.generateIncrement(inSolicitudRequest,
                            cliente, req.getRemoteAddr());
                    apiResponse = new ApiResponse(respIncr.getBody().getMessage(),
                            String.valueOf(HttpStatus.OK.value()), HttpStatus.OK, new Date(), null);
                } else {
                    logger.error("El cliente  : " + cliente.getClieIdentificacion()
                            + " ha ingresado un monto distinto al que puede ahorrar Solicitud => monto enviado="
                            + inSolicitudRequest.getMonto() + " \t monto actual=" + cliente.getClieMontact()
                            + "\t monto máximo=" + cliente.getClieMontmaxi(), LOGGER_RESPONSE_FORMAT);
                    apiResponse = new ApiResponse("El monto enviado es mayor o menor referente al monto permitido",
                            String.valueOf(HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT, new Date(), null);
                }
            }else{
                logger.info("El cliente " + cliente.getClieIdentificacion() + " no ha recibido la otp  ",
                        LOGGER_RESPONSE_FORMAT);
                apiResponse = new ApiResponse(response.getBody().getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.BAD_REQUEST, new Date(), null);
            }

        } catch (ApiRequestException e) {
            logger.error(" Error al momento de consultar la informacion del cliente en la validación de otp : " + e
                    + " \t en el metodo validateOtp");
            apiResponse = new ApiResponse(e.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST, new Date(), null);
        }

        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }


}
