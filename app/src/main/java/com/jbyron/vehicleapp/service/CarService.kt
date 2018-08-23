package com.jbyron.vehicleapp.service

import com.jbyron.vehicleapp.BuildConfig
import io.reactivex.Observable
import com.jbyron.vehicleapp.model.Car
import retrofit2.http.GET

interface CarService {

    companion object {
        internal const val BASE_URL = BuildConfig.BASE_URL
    }

    @GET("vehicle-attributes.json")
    fun get(): Observable<Car>

}