package com.santander.base.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmisionTarjetaRequest (
    @NotNull @Pattern(regexp = "\\d{6}", message = "debe tener exactamente 6 digitos numericos") String prodctId,
    @NotBlank @Size(max = 100) String holderName){

}
