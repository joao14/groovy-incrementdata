package com.pichincha.marketing.model.otp.request.body;

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
public class Ordenante {

	@JsonPropertyOrder("1")
	@JsonAlias("identificacion")
	@JsonProperty("identificacion")
	String identificacion;

	 @JsonPropertyOrder("2")
	 @JsonAlias("tipoIdentificacion")
	 @JsonProperty("tipoIdentificacion")
	 String tipoIdentificacion;



}
