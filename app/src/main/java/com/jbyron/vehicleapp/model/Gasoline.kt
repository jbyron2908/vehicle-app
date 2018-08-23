package com.jbyron.vehicleapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Gasoline(
    @JsonProperty("average_consumption") val averageConsumption: AverageConsumption
)