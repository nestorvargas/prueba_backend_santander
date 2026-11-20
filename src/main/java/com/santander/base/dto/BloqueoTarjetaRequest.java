package com.santander.base.dto;

import jakarta.validation.constrains.Size;


public class BloqueoTarjetaRequest
        (@Size(max = 100) String reason ) {

}
