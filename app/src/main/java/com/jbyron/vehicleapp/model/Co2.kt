package com.jbyron.vehicleapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Co2(
    @JsonProperty("mixed") val mixed: Double
)