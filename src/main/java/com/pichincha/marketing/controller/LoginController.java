package com.pichincha.marketing.controller;

import com.pichincha.marketing.dto.InCliente;
import com.pichincha.marketing.exception.ApiRequestException;
import com.pichincha.marketing.repository.IBasecampaDao;
import com.pichincha.marketing.repository.IClienteDao;
import com.pichincha.marketing.util.ApiResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LoginController {
	private static final String LOGGER_RESPONSE_FORMAT = "001-RES";
	private static final String LOGGER_REQUEST_FORMAT = "001-REQ";
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Value("${jwt.timeout}")
	private String timeout;

	@Value("${jwt.secret}")
	private String secret;

	@Autowired
	private IClienteDao iclienteDao;

	@Autowired
	private IBasecampaDao iBasecampaDao;

	@RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public ResponseEntity<?> getLogin(@RequestParam(value = "hash") String hash, HttpServletRequest request) {
		String method = "getLogin";
		logger.info("- Generando un token de seguridad para el hash  : " + hash + " desde la IP \t"
				+ request.getRemoteAddr(), method, LOGGER_REQUEST_FORMAT);
		HttpHeaders headers = new HttpHeaders();
		ApiResponse apiResponse = null;
		Integer encontro = 0;
		try {			
			InCliente cliente = iclienteDao.findByCliente(hash);			
			Integer baca_id = cliente.getBacaId().getBacaId();
			String campana = iBasecampaDao.findByBaseCampana(baca_id).getBacaDescripcion();
			encontro = iclienteDao.findByClienteIncrementByAccept(hash);
			if (encontro > 0) {
				logger.info(
						"El cliente encontrado : \t " + cliente.getClieIdentificacion() + " - "
								+ cliente.getCliePrimnomb() + " " + cliente.getCliePrimapel()
								+ " ya ha realizado un aumento en la campaña  \t" + campana,
						LOGGER_RESPONSE_FORMAT, method);
				apiResponse = new ApiResponse("El cliente ya ha realizado un aumento en la campaña " + campana,
						String.valueOf(HttpStatus.EXPECTATION_FAILED.value()), HttpStatus.EXPECTATION_FAILED,
						new Date(), null);
			} else {
				logger.info(
						"El cliente encontrado : \t " + cliente.getClieIdentificacion() + " - "
								+ cliente.getCliePrimnomb() + " " + cliente.getCliePrimapel()
								+ " aún no ha realizado un aumento en la campaña. \t" + campana,
						LOGGER_RESPONSE_FORMAT, method);
				String token = this.getJWTToken(cliente.getClieIdenenma(), cliente.getClieEmail(),
						cliente.getCliePrimnomb() + "" + cliente.getCliePrimapel());
				logger.info("El cliente: \t " + cliente.getClieIdentificacion() + " generó el token : \t " + token,
						LOGGER_RESPONSE_FORMAT, method);
				apiResponse = new ApiResponse("Si podrá realizar el aumento de ahorro para la campaña " + campana,
						String.valueOf(HttpStatus.OK.value()), HttpStatus.OK, new Date(), token);
			}
		} catch (ApiRequestException e) {
			logger.error("Se ha detectado un error en la clase getLogin", LOGGER_RESPONSE_FORMAT, method);
			apiResponse = new ApiResponse("No se pudo generar el token de seguridad",
					String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST, new Date(), null);
		} catch (Exception e) {
			logger.error("Se ha producido un error al momento de la petición del JWT " + e);
			apiResponse = new ApiResponse("Error al momento de obtener el token de seguridad",
					String.valueOf(HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST, new Date(), null);

		}
		return new ResponseEntity<Object>(apiResponse, headers, apiResponse.getStatus());
	}

	/**
	 * Method that allows you to obtain the security token.
	 *
	 * @param identificacion
	 * @param email
	 * @param nombre
	 * @return
	 */
	private String getJWTToken(String identificacion, String email, String nombre) {
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
		String token = Jwts.builder().setId("softtekJWT").setSubject(identificacion).setSubject(email)
				.setSubject(nombre)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Integer.valueOf(timeout)))// 6 minutos
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();
		logger.info("El token generado para el cliente " + identificacion + " \t es" + token);
		return "Bearer " + token;
	}
}
