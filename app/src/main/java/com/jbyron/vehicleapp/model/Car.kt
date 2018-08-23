package com.jbyron.vehicleapp.model

import com.fasterxml.jackson.annotation.JsonProperty

data class Car(
    @JsonProperty("regno") val regno: String,
    @JsonProperty("vin") val vin: String,
    @JsonProperty("timestamp") val timestamp: String,
    @JsonProperty("emission") val emission: Emission,
    @JsonProperty("fuel") val fuel: Fuel,
    @JsonProperty("gearbox_type") val gearboxType: String,
    @JsonProperty("year") val year: Int,
    @JsonProperty("brand") val brand: String,
    @JsonProperty("fuel_types") val fuelTypes: List<String>
)