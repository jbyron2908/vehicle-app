package com.jbyron.vehicleapp

import android.app.Application
import com.jbyron.vehicleapp.dagger.AppComponent
import com.jbyron.vehicleapp.dagger.AppModule
import com.jbyron.vehicleapp.dagger.DaggerAppComponent
import timber.log.Timber

class App : Application() {
    companion object {
        @JvmStatic
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        // Timber logging
        setupTimber()

        // Dagger dependency injection
        setupDagger()
    }

    /*
     * Private Methods
     */

    private fun setupTimber() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
    }

    private fun setupDagger() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        component.inject(this)
    }
}
