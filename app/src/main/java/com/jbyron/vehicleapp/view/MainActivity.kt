package com.jbyron.vehicleapp.view

import android.os.Bundle
import com.jbyron.vehicleapp.App
import com.jbyron.vehicleapp.R
import com.jbyron.vehicleapp.util.FragmentUtil
import com.jbyron.vehicleapp.view.car.CarFragment

class MainActivity : BaseActivity() {

    init {
        App.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentUtil.put(this, CarFragment.newInstance())
    }
}
