package com.jbyron.vehicleapp.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import com.jbyron.vehicleapp.util.FragmentUtil
import com.jbyron.vehicleapp.util.LocationUtil
import com.jbyron.vehicleapp.util.PreferencesUtil
import com.jbyron.vehicleapp.util.UiUtil
import com.jbyron.vehicleapp.view.MainActivity
import javax.inject.Singleton

@Module
class UtilsModule {
    @Provides
    @Singleton
    fun providePreferencesUtil(context: Context): PreferencesUtil = PreferencesUtil(context)

    @Provides
    @Singleton
    fun provideLocationUtil(context: Context): LocationUtil = LocationUtil(context)

    @Provides
    @Singleton
    fun provideUiUtil(context: Context): UiUtil = UiUtil(context)

    @Provides
    @Singleton
    fun provideFragmentUtil(activity: MainActivity): FragmentUtil = FragmentUtil(activity)
}