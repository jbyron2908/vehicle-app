package com.jbyron.vehicleapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Emission(
    @JsonProperty("gasoline") val gasoline: GasolineX
)