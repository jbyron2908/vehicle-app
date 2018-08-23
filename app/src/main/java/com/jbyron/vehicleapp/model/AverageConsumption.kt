package com.jbyron.vehicleapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class AverageConsumption(
    @JsonProperty("urban") val urban: Double,
    @JsonProperty("rural") val rural: Double,
    @JsonProperty("mixed") val mixed: Double
)