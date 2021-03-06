package com.pichincha.marketing.controller;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.exception.ApiRequestException;
import com.pichincha.marketing.model.InClienteResponse;
import com.pichincha.marketing.repository.IClienteDao;
import com.pichincha.marketing.services.LogsConvService;
import com.pichincha.marketing.util.ApiResponse;
import com.pichincha.marketing.util.UtilResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class ClienteController {

    private static final String LOGGER_RESPONSE_FORMAT = "001-RES";
    private static final String LOGGER_REQUEST_FORMAT = "001-REQ";
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private IClienteDao iclienteDao;
    @Autowired
    private LogsConvService logsConvService;

    @GetMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getCliente(@RequestParam(value = "hash") String hash, HttpServletRequest request) {
        String method = "getCliente";
        logger.info("- Obteniendo la información del cliente con el hash  : " + hash + " desde la IP \t"
                + request.getRemoteAddr(), method, LOGGER_REQUEST_FORMAT);
        ApiResponse apiResponse = new ApiResponse();
        try {
            InCliente cliente = iclienteDao.findByCliente(hash);
            if (cliente == null) {
                logger.info(" El cliente no se ha encontrado en el método getCliente con el hash" + hash);
                apiResponse = new ApiResponse("Cliente no encontrado", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.BAD_REQUEST, new Date(), null);
            } else {
                //Se almacenara en la tabla de logs de conversion
                logsConvService.saveLogsConversion(cliente);
                //Se devolvara la informacion del cliente
                InClienteResponse clientes = new InClienteResponse(cliente.getCliePrimnomb(), cliente.getClieMontact(),
                        String.valueOf(Integer.parseInt(cliente.getClieMontact()) + 5), cliente.getClieMontmaxi(),
                        cliente.getClieMontsuge(),
                        cliente.getClieEmail().equals("") || cliente.getClieEmail().isEmpty() ? ""
                                : UtilResponse.enmascarar_email(cliente.getClieEmail()),
                        cliente.getClieCelular().equals("") || cliente.getClieCelular().isEmpty() ? ""
                                : UtilResponse.enmascarar_cedula(cliente.getClieCelular()),
                        cliente.getClieHashcmb(), String.valueOf(cliente.getCliePremio()),
                        cliente.getClieTipoahorfutu() != null ?cliente.getClieTipoahorfutu().toUpperCase().equals("PLAN AHORRO FUTURO RESERVA") ? "2" : "1":"PLAN AHORRO FUTURO RESERVA"
                );
                logger.info(
                        " El cliente se ha encontrado con la identificación : \t" + cliente.getClieIdentificacion());
                apiResponse = new ApiResponse("Cliente encontrado", String.valueOf(HttpStatus.OK.value()),
                        HttpStatus.OK, new Date(), clientes);
            }
        } catch (ApiRequestException e) {
            logger.error(
                    " Error al momento de consultar la informacion del cliente : " + e + "en el metodo getCliente");
            apiResponse = new ApiResponse(e.getMessage(), String.valueOf(HttpStatus.CONFLICT.value()),
                    HttpStatus.CONFLICT, new Date(), null);
        } catch (Exception e) {
            logger.error(
                    " Error al momento de consultar la informacion del cliente : " + e + "en el metodo getCliente");
            apiResponse = new ApiResponse(e.getMessage(), String.valueOf(HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.BAD_REQUEST, new Date(), null);
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }

}
