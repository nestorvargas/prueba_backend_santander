package com.santander.base.dto;

import jakarta.validation.constraints.Size;

public record BloqueoTarjetaRequest(@Size(max = 100) String reason) {
}
