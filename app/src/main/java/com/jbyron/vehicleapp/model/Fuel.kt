package com.jbyron.vehicleapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Fuel(
    @JsonProperty("gasoline") val gasoline: Gasoline
)