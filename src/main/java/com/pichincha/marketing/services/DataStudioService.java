package com.pichincha.marketing.services;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.dto.InDatastudio;
import com.pichincha.marketing.model.InSolicitudRequest;
import com.pichincha.marketing.repository.IDataStudioDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
public class DataStudioService {

    private static final String LOGGER_RESPONSE_FORMAT = "004-RES";
    private static final String LOGGER_REQUEST_FORMAT = "004-REQ";
    private static final Logger logger = LoggerFactory.getLogger(DataStudioService.class);
    final Date date = new Date();
    final ZoneId id = ZoneId.systemDefault();
    @Autowired
    private IDataStudioDao iDataStudioDao;

    /**
     * Funcion permitida para guardar los incrementos para la consulta en Data Studio
     *
     * @param cliente
     * @param inSolicitudRequest
     */
    public void saveDatStudio(InCliente cliente, InSolicitudRequest inSolicitudRequest) {
        logger.info("Se guardara el incremento para el Data Studio en la tabla InDatastudio con el cliente"
                + cliente.getClieIdentificacion());
        try {
            InDatastudio dataStudio = new InDatastudio();
            dataStudio.setDaesAgenventa("DIGITAL");
            dataStudio.setDaesIdentificacion(cliente.getClieIdentificacion());
            dataStudio.setDaesNombre(cliente.getCliePrimnomb() + " " + cliente.getClieSegunomb() + " "
                    + cliente.getCliePrimapel() + " " + cliente.getClieSeguapel());
            dataStudio.setDaesTipoahorr("PLAN AHORRO FUTURO");
            dataStudio.setDaesCuenahorrfutu(cliente.getClieCuenahorfutu());
            dataStudio.setDaesMontbasemadr(cliente.getClieMontact());
            dataStudio.setDaesValototal(inSolicitudRequest.getMonto());
            dataStudio.setDaesValoincre(Integer.parseInt(inSolicitudRequest.getMonto())
                    - Integer.parseInt(cliente.getClieMontact()) + "");
            dataStudio.setDaesBacaid(cliente.getBacaId().getBacaId());
            iDataStudioDao.save(dataStudio);

        } catch (Exception e) {
            logger.error("No se ha podido ingresar el incremento de ahorro una vez aprobada la solicitud : "
                    + e.getMessage(), LOGGER_RESPONSE_FORMAT);
        }
    }

}
