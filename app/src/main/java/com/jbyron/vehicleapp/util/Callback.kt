package com.jbyron.vehicleapp.util

interface Callback<T> {
    fun response(response: T)
}