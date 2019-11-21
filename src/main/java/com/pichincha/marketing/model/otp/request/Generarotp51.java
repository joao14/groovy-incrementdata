package com.pichincha.marketing.model.otp.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.pichincha.marketing.model.otp.request.body.BodyIn;
import com.pichincha.marketing.model.otp.request.header.Headerin;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Generarotp51 {

	@JsonPropertyOrder("1")
	@JsonAlias("headerIn")
	@JsonProperty("headerIn")
	Headerin headerIn;

	 @JsonPropertyOrder("2")
	 @JsonAlias("bodyIn")
	 @JsonProperty("bodyIn")
	 BodyIn bodyIn;
}
