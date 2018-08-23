package com.jbyron.vehicleapp.dagger

import dagger.Component
import com.jbyron.vehicleapp.App
import com.jbyron.vehicleapp.view.MainActivity
import com.jbyron.vehicleapp.view.car.CarFragment
import com.jbyron.vehicleapp.viewmodel.CarViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    UtilsModule::class,
    ViewModelModule::class
])

interface AppComponent {
    // App
    fun inject(target: App)

    // Activities
    fun inject(target: MainActivity)

    // Fragments
    fun inject(target: CarFragment)

    // View Models
    fun inject(target: CarViewModel)
}
