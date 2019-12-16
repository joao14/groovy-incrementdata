package com.pichincha.marketing.model.otp.request.header;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Headerin {

	@JsonPropertyOrder("1")
	@JsonAlias("dispositivo")
	@JsonProperty("dispositivo") 
	String dispositivo; 

	@JsonPropertyOrder("2")
	@JsonAlias("empresa")
	@JsonProperty("empresa")
	String empresa;

	@JsonPropertyOrder("3")
	@JsonAlias("canal")
	@JsonProperty("canal")
	String canal;

	@JsonPropertyOrder("4")
	@JsonAlias("medio")
	@JsonProperty("medio")
	String medio;

	@JsonPropertyOrder("5")
	@JsonAlias("aplicacion")
	@JsonProperty("aplicacion")
	String aplicacion;

	@JsonPropertyOrder("6")
	@JsonAlias("agencia")
	@JsonProperty("agencia")
	String agencia;

	@JsonPropertyOrder("7")
	@JsonAlias("tipoTransaccion")
	@JsonProperty("tipoTransaccion")
	String tipoTransaccion;

	@JsonPropertyOrder("8")
	@JsonAlias("geolocalizacion")
	@JsonProperty("geolocalizacion")
	String geolocalizacion;

	@JsonPropertyOrder("9")
	@JsonAlias("usuario")
	@JsonProperty("usuario")
	String usuario;

	@JsonPropertyOrder("10")
	@JsonAlias("unicidad")
	@JsonProperty("unicidad")
	String unicidad;

	@JsonPropertyOrder("11")
	@JsonAlias("guid")
	@JsonProperty("guid")
	String guid;

	@JsonPropertyOrder("12")
	@JsonAlias("fechaHora")
	@JsonProperty("fechaHora")
	String fechaHora;


	@JsonPropertyOrder("13")
	@JsonAlias("filler")
	@JsonProperty("filler")
	String filler;

	@JsonPropertyOrder("14")
	@JsonAlias("idioma")
	@JsonProperty("idioma")
	String idioma;

	 @JsonPropertyOrder("15")
	 @JsonAlias("sesion")
	 @JsonProperty("sesion")
	 String sesion;

	 @JsonPropertyOrder("16")
	 @JsonAlias("ip")
	 @JsonProperty("ip")
	 String ip;

	 @JsonPropertyOrder("17")
	 @JsonAlias("idCliente")
	 @JsonProperty("idCliente")
	 String idCliente;

	 @JsonPropertyOrder("18")
	 @JsonAlias("tipoIdCliente")
	 @JsonProperty("tipoIdCliente")
	 String tipoIdCliente;

















}
