package com.pichincha.marketing.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.util.Base64;
import com.pichincha.marketing.model.otp.Request;
import com.pichincha.marketing.model.otp.RequestValidate;
import com.pichincha.marketing.model.otp.Response;
import com.pichincha.marketing.model.otp.ResponseValidate;
import com.pichincha.marketing.model.otp.request.EvaluarOTP52;
import com.pichincha.marketing.model.otp.request.Generarotp51;
import com.pichincha.marketing.model.otp.request.body.*;
import com.pichincha.marketing.model.otp.request.header.Headerin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class OtpService {

    private static final String LOGGER_RESPONSE_FORMAT = "004-RES";
    private static final String LOGGER_REQUEST_FORMAT = "004-REQ";
    private static final Logger logger = LoggerFactory.getLogger(OtpService.class);
    @Autowired
    Environment env;

    RestTemplate restTemplate = new RestTemplate();

    /**
     * Funcion quqe permite obtener la respuesta del cliente junto con el codigo OTP
     *
     * @param idCliente
     * @return
     */
    public ResponseEntity<Response> generateOTP(String idCliente) {
        ResponseEntity<Response> response = null;
        String json;
        ObjectMapper mapper = new ObjectMapper();
        String method = "generateOTP";
        String log = String.join("-", LOGGER_REQUEST_FORMAT, method);
        logger.info(log, idCliente);
        String url = env.getRequiredProperty("core.url");        
        Request request = Request.builder()
                .GenerarOTP51(Generarotp51.builder()
                        .headerIn(this.getHeaders(idCliente, "S")) //Significa envio de nuevo OTP
                        .bodyIn(BodyIn.builder()
                                .ordenante(Ordenante.builder()
                                        .identificacion(idCliente)
                                        .tipoIdentificacion(env.getRequiredProperty("core.header.tipoIdCliente"))
                                        .build())
                                .transaccion(Transaccion.builder()
                                        .sesion(env.getRequiredProperty("core.body.sesion"))
                                        .ip(env.getRequiredProperty("core.body.ip"))
                                        .tipoTransaccion(env.getRequiredProperty("core.body.tipoTransaccion"))
                                        .tiempoVida(env.getRequiredProperty("core.body.tiempoVida"))
                                        .monto(env.getRequiredProperty("core.body.monto"))
                                        .build())
                                .build())
                        .build())
                .build();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS");
        request.getGenerarOTP51().getHeaderIn().setFechaHora(dateTimeFormatter.format(LocalDateTime.now()));

        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = env.getRequiredProperty("core.header.auth.usuario") + ":" + env.getRequiredProperty("core.header.auth.clave");
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        httpHeaders.add("Authorization", authHeader);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);        
        try {            
            json = mapper.writeValueAsString(request);
            log = String.join("-", LOGGER_REQUEST_FORMAT, method, "{}");
            logger.info(log, json);
        } catch (JsonProcessingException e) {
            log = String.join("-", LOGGER_RESPONSE_FORMAT, method);
            logger.error(log, e);
        }
        try {           
            response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(request, httpHeaders),
                    new ParameterizedTypeReference<Response>() {
                    }
            );
            json = mapper.writeValueAsString(response);
            log = String.join("-", LOGGER_RESPONSE_FORMAT, method, "{}");
            logger.info(log, json);
            if (response.getBody() != null &&
                    response.getBody().getGenerarOTP51Response() != null &&  
                    response.getBody().getGenerarOTP51Response().getError() != null &&  
                    response.getBody().getGenerarOTP51Response().getError().getCodigo() != null &&
                    response.getBody().getGenerarOTP51Response().getError().getCodigo().equals("0") &&  
                    response.getBody().getGenerarOTP51Response().getBodyOut() != null &&
                    response.getBody().getGenerarOTP51Response().getBodyOut().getIdTransaccion() != null
            ) {
                log = String.join("-", LOGGER_RESPONSE_FORMAT,
                        method, "{}");
                logger.info(log, response);

                //return response.getBody().getGenerarOTP51Response().getBodyOut().getIdTransaccion();
                //return response.getBody().getGenerarOTP51Response();
            } else {
                log = String.join("-", LOGGER_RESPONSE_FORMAT,
                        method, "Ocurrio un error", "{}");
                logger.info(log, response);
            }
        } catch (Exception e) {
            log = String.join("-", method);
            logger.error(log, e);
        }        
        return response;
    }

    /**
     * Funcion que permite validar el otp enviado por el cliente
     *
     * @param idCliente
     * @param otp
     * @return
     */
    public ResponseEntity<ResponseValidate> validateOtp(String idCliente, String otp) {
        ResponseEntity<ResponseValidate> response = null;
        String json;
        ObjectMapper mapper = new ObjectMapper();
        String method = "validateOtp";
        String log = String.join("-", LOGGER_REQUEST_FORMAT, method);
        logger.info(log, idCliente);
        String url = env.getRequiredProperty("core.url");
        RequestValidate request = RequestValidate.builder()
                .EvaluarOTP52(EvaluarOTP52.builder()
                        .headerIn(this.getHeaders(idCliente, "G")) //G que significa obtener OTP
                        .bodyIn(BodyValiateIn.builder()
                                .otp(Otp.builder()
                                        .otp(otp)
                                        .reintentos("3").build()
                                )
                                .ordenante(Ordenante.builder()
                                        .identificacion(idCliente)
                                        .tipoIdentificacion(env.getRequiredProperty("core.header.tipoIdCliente"))
                                        .build())
                                .transaccion(TransaccionValidate.builder()
                                        .sesion(env.getRequiredProperty("core.body.sesion"))
                                        .tipoTransaccion(env.getRequiredProperty("core.body.tipoTransaccion"))
                                        .build())
                                .build())
                        .build())
                .build();

        //Ingresa la fecha de consulta en el request
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS");
        request.getEvaluarOTP52().getHeaderIn().setFechaHora(dateTimeFormatter.format(LocalDateTime.now()));
        //Setea el header para la consulta
        HttpHeaders httpHeaders = new HttpHeaders();
        String auth = env.getRequiredProperty("core.header.auth.usuario") + ":" + env.getRequiredProperty("core.header.auth.clave");
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        httpHeaders.add("Authorization", authHeader);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);

        try {
            json = mapper.writeValueAsString(request);
            log = String.join("-", LOGGER_REQUEST_FORMAT, method, "{}");
            logger.info(log, json);
        } catch (JsonProcessingException e) {
            log = String.join("-", LOGGER_RESPONSE_FORMAT, method);
            logger.error(log, e);
        }
        try {
            response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(request, httpHeaders),
                    new ParameterizedTypeReference<ResponseValidate>() {
                    }
            );
            json = mapper.writeValueAsString(response);  
            log = String.join("-", LOGGER_RESPONSE_FORMAT, method, "{}");   
            logger.info(log, json);   
            if (response.getBody() != null &&
                    response.getBody().getEvaluarOTP52Response() != null &&
                    response.getBody().getEvaluarOTP52Response().getError() != null &&   
                    response.getBody().getEvaluarOTP52Response().getError().getCodigo() != null &&
                    response.getBody().getEvaluarOTP52Response().getError().getCodigo().equals("0") &&

                    response.getBody().getEvaluarOTP52Response().getBodyOut() != null &&
                    response.getBody().getEvaluarOTP52Response().getBodyOut().getReintetos() != null   
            ) {
                log = String.join("-", LOGGER_RESPONSE_FORMAT,
                        method, "{}");
                logger.info(log, response);

                //return response.getBody().getGenerarOTP51Response().getBodyOut().getIdTransaccion();
                //return response.getBody().getGenerarOTP51Response();
            } else {
                log = String.join("-", LOGGER_RESPONSE_FORMAT,
                        method, "Ocurrio un error", "{}");
                logger.info(log, response);
            }
        } catch (Exception e) {
            log = String.join("-", method);
            logger.error(log, e);
        }
        return response;

    }


    /**
     * Funcion que crea el Headerin para el request
     *
     * @param idCliente
     * @return
     */
    private Headerin getHeaders(String idCliente, String type) {
        return Headerin.builder()      
                .dispositivo(env.getRequiredProperty("core.header.dispositivo"))   
                .empresa(env.getRequiredProperty("core.header.empresa"))
                .canal(env.getRequiredProperty("core.header.canal"))
                .medio(env.getRequiredProperty("core.header.medio"))   
                .aplicacion(env.getRequiredProperty("core.header.aplicacion"))
                .agencia(env.getRequiredProperty("core.header.agencia"))
                .tipoTransaccion(env.getRequiredProperty(type.equals("S") ? "core.header.tipoTransaccion" : "core.header.tipoTransaccionValidate"))
                .geolocalizacion(env.getRequiredProperty("core.header.geolocalizacion"))
                .usuario(env.getRequiredProperty("core.header.usuario"))
                .unicidad(env.getRequiredProperty("core.header.unicidad"))
                .guid(env.getRequiredProperty("core.header.guid"))
                .filler(env.getRequiredProperty("core.header.filler"))
                .idioma(env.getRequiredProperty("core.header.idioma"))
                .ip(env.getRequiredProperty("core.header.ip"))
                .idCliente(idCliente)
                .tipoIdCliente(env.getRequiredProperty("core.header.tipoIdCliente"))
                .build();
    }
}
