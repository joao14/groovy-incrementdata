package com.pichincha.marketing.services;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.dto.InLogs;
import com.pichincha.marketing.dto.InLogsconversion;
import com.pichincha.marketing.model.InSolicitudRequest;
import com.pichincha.marketing.repository.ILogsConvDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
public class LogsConvService {

    @Autowired
    private ILogsConvDao iLogsConvDao;

    private static final String LOGGER_RESPONSE_FORMAT = "004-RES";
    private static final String LOGGER_REQUEST_FORMAT = "004-REQ";
    private static final Logger logger = LoggerFactory.getLogger(LogsConvService.class);

    /**
     * Funcion que permite guardar los logs de conversion para los clientes ingresados
     *
     * @param cliente
     */
    public void saveLogsConversion(InCliente cliente) {
        //Valida si el cliente ya ingreso a la campania
        try {
            InLogsconversion inLogsconversion = iLogsConvDao.findByClient(cliente.getBacaId().getBacaId(), cliente.getClieIdentificacion());
            logger.info("Se guardara el posible incremento para el cliente : \t" + cliente.getClieIdentificacion()
                    + " en la tabla InLogsconversion", LOGGER_RESPONSE_FORMAT);

            if (inLogsconversion == null) {
                InLogsconversion log = new InLogsconversion();
                log.setBacaId(cliente.getBacaId());
                log.setLocoHash(cliente.getClieHashiden());
                log.setLocoIdentificacion(cliente.getClieIdentificacion());
                log.setLocoNombapell(cliente.getCliePrimnomb() + " " + cliente.getClieSegunomb() + " " + cliente.getCliePrimapel() + " " + cliente.getClieSeguapel());
                log.setLocoEstado('1');
                log.setLocoPantalla("1");
                iLogsConvDao.save(log);
            }
        } catch (Exception e) {
            logger.error("No se ha podido ingresar el log de conversion para el cliente ingresado : "
                    + e.getMessage(), LOGGER_RESPONSE_FORMAT);
        }
    }

    /**
     * Funcion que permite actualizar los logs de conversion
     *
     * @param cliente
     */
    public void updateLogsConversion(InCliente cliente, String pantalla) {
        try {
            InLogsconversion inLogsconversion = iLogsConvDao.findByClient(cliente.getBacaId().getBacaId(), cliente.getClieIdentificacion());
            logger.info("Se actualizara el posible incremento para el cliente : \t" + cliente.getClieIdentificacion()
                    + " en la tabla InLogsconversion", LOGGER_RESPONSE_FORMAT);
            if (inLogsconversion != null) {
                iLogsConvDao.updateLogsConv(pantalla, inLogsconversion.getLocoId());
            }
        } catch (Exception e) {
            logger.error("No se ha podido actualizar el log de conversion para el cliente ingresado : "
                    + e.getMessage(), LOGGER_RESPONSE_FORMAT);
        }
    }

    /**
     * Funcion que permite actualizar los logs de conversion
     *
     * @param cliente
     */
    public void deleteLogsConversion(InCliente cliente) {
        try {
            InLogsconversion inLogsconversion = iLogsConvDao.findByClient(cliente.getBacaId().getBacaId(), cliente.getClieIdentificacion());
            logger.info("Se eliminara el posible incremento para el cliente : \t" + cliente.getClieIdentificacion()
                    + " en la tabla InLogsconversion", LOGGER_RESPONSE_FORMAT);
            if (inLogsconversion != null) {
                iLogsConvDao.deleteLogsConv(inLogsconversion.getLocoId());
                logger.info("Se elimino correctamente");
            }
        } catch (Exception e) {
            logger.error("No se ha podido eliminar el log de conversion para el cliente ingresado : "
                    + e.getMessage(), LOGGER_RESPONSE_FORMAT);
        }
    }
}
