package com.jbyron.vehicleapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class GasolineX(
    @JsonProperty("co2") val co2: Co2
)