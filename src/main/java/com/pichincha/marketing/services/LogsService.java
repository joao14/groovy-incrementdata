package com.pichincha.marketing.services;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.dto.InLogs;
import com.pichincha.marketing.model.InSolicitudRequest;
import com.pichincha.marketing.repository.ILogsDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.util.Date;

@Service
public class LogsService {

	final Date date = new Date();
	final ZoneId id = ZoneId.systemDefault();
	private static final String LOGGER_RESPONSE_FORMAT = "004-RES";
	private static final String LOGGER_REQUEST_FORMAT = "004-REQ";
	private static final Logger logger = LoggerFactory.getLogger(LogsService.class);

	@Autowired
	private ILogsDao ilogDao;

	/**
	 * Funcion permitida para guardar los logs de los clientes al finalizar el
	 * ahorro incremento
	 * 
	 * @param cliente
	 * @param inSolicitudRequest
	 * @param ip
	 */
	public void saveLogs(InCliente cliente, InSolicitudRequest inSolicitudRequest, String ip) {
		logger.info("Se guardara el incremento para el cliente : \t" + cliente.getClieIdentificacion()
				+ " en la tabla InLogs", LOGGER_RESPONSE_FORMAT);		
		try {
			InLogs log = new InLogs();
			log.setLogIdentificacion(cliente.getClieIdentificacion());
			log.setLogCuenahorfutu(cliente.getClieCuenahorfutu());
			log.setLogCuenorig(cliente.getClieCuenorig() == null ? "" : cliente.getClieCuenorig());
			log.setLogNombapell(cliente.getCliePrimnomb() + " " + cliente.getCliePrimapel());
			log.setLogMontante(cliente.getClieMontact());
			log.setLogMontnuev(inSolicitudRequest.getMonto());
			log.setLogOtp(inSolicitudRequest.getOtp());
			log.setLogIp(ip == null ? "" : ip);
			ilogDao.save(log);
		} catch (Exception e) {
			logger.error("No se podido ingresar el log de incremento ahorro una vez aprobada la solicitud : "
					+ e.getMessage(), LOGGER_RESPONSE_FORMAT);
		}
	}
}
