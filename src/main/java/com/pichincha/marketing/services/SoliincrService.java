package com.pichincha.marketing.services;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.dto.InSoliincr;
import com.pichincha.marketing.exception.ApiRequestException;
import com.pichincha.marketing.model.InSolicitudRequest;
import com.pichincha.marketing.repository.ISoliincrDao;
import com.pichincha.marketing.util.ApiResponse;
import com.pichincha.marketing.util.UtilResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.Date;

@Service
public class SoliincrService {

    private static final String LOGGER_RESPONSE_FORMAT = "004-RES";
    private static final String LOGGER_REQUEST_FORMAT = "004-REQ";
    private static final Logger logger = LoggerFactory.getLogger(SoliincrService.class);
    final Date date = new Date();
    final ZoneId id = ZoneId.systemDefault();
    @Autowired
    private ISoliincrDao IsoliincrDao;
    @Autowired
    private LogsService logsService;

    public ResponseEntity<ApiResponse> generateIncrement(InSolicitudRequest inSolicitudRequest, InCliente cliente, String ip) {
        logger.info("Se generara un incremento para el cliente " + cliente.getCliePrimnomb());
        ApiResponse apiResponse = null;
        try {
            logger.info("El cliente encontrado para el incremento del monto con el hash " + inSolicitudRequest.getHash() + "\t es \t " + cliente.getCliePrimnomb() + " " + cliente.getCliePrimapel());
            Timestamp timestamp = new Timestamp(new Date().getTime());
            logger.info("FECHA CONVERTIDA");
            logger.info(timestamp + "AA");
            logger.info(UtilResponse.getTimeStamp() + "BB");
            InSoliincr inSoliincr = new InSoliincr('S', inSolicitudRequest.getMonto(), inSolicitudRequest.getOtp(), '0', cliente, inSolicitudRequest.getHash());
            final InSoliincr solicitiud = IsoliincrDao.save(inSoliincr);
            if (solicitiud != null) {
                logger.info("Se ha realizado el incremento de : " + solicitiud.getSoinMontacep() + "\t para el cliente " + cliente.getClieIdentificacion());
                apiResponse = new ApiResponse("Se ha realizado el incremento correctamente", String.valueOf(HttpStatus.OK.value()), HttpStatus.OK, new Date(), null);
                //Se guarda los logs en la tabla historica
                logsService.saveLogs(cliente, inSolicitudRequest, ip);
            } else {
                logger.warn("No se ha realizado el incremento de : " + solicitiud.getSoinMontacep() + "\t para el cliente " + cliente.getClieIdentificacion());
                apiResponse = new ApiResponse("Ups ha sucedido algún inconveniente", String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST, new Date(), null);
            }

        } catch (ApiRequestException e) {
            logger.error("Uups, surgio un error al realizar un incremento con el usuario \t " + inSolicitudRequest.getHash(), e);
            throw new ApiRequestException("No se pudo generar el token de seguridad");
        }
        return new ResponseEntity<>(apiResponse, apiResponse.getStatus());
    }
}
