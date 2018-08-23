package com.jbyron.vehicleapp.dagger

import dagger.Module
import dagger.Provides
import com.jbyron.vehicleapp.viewmodel.CarViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideCarViewModel(): CarViewModel = CarViewModel()
}