package com.jbyron.vehicleapp.viewmodel

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import com.jbyron.vehicleapp.App
import com.jbyron.vehicleapp.constant.NetworkState
import com.jbyron.vehicleapp.model.Car
import com.jbyron.vehicleapp.service.CarService
import com.jbyron.vehicleapp.service.ServiceFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CarViewModel {

    @Inject
    lateinit var sf: ServiceFactory

    val service: CarService

    init {
        App.component.inject(this)
        service = sf.create(CarService::class.java, 5, TimeUnit.MINUTES)
    }

    // Subjects
    val state = BehaviorSubject.create<NetworkState>()!!
    val car = BehaviorSubject.create<Car>()!!

    /*
     * API Calls
     */

    fun getCar(): Observable<Car> = service.get()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe { state.onNext(NetworkState.LOADING) }
        .doOnNext { car.onNext(it) }
        .doOnError {
            Timber.e(it, "Error getting the car by code")
            state.onNext(NetworkState.ERROR)
        }
        .doOnComplete { state.onNext(NetworkState.IDLE) }
}
