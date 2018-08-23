package com.jbyron.vehicleapp.view.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jbyron.vehicleapp.App
import com.jbyron.vehicleapp.R
import com.jbyron.vehicleapp.view.BaseFragment
import com.jbyron.vehicleapp.viewmodel.CarViewModel
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_car.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class CarFragment : BaseFragment() {
    companion object {
        fun newInstance() = CarFragment()
    }

    @Inject
    lateinit var carViewModel: CarViewModel

    init {
        App.component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.fragment_car, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadCarData()
    }

    override fun bindViewModel() {
        val doubleFormat = "%.6f"
        disposables.addAll(
            carViewModel.car.subscribe {
                tvRegistrationNumber.text = it.regno.toUpperCase()
                tvVIN.text = it.vin.toUpperCase()
                tvBrand.text = it.brand.capitalize()
                tvYear.text = it.year.toString()
                tvGearBox.text = it.gearboxType.capitalize()
                tvFuel.text = it.fuelTypes.joinToString { it.capitalize() }
                tvConsumptionUrban.text = doubleFormat.format(it.fuel.gasoline.averageConsumption.urban)
                tvConsumptionRural.text = doubleFormat.format(it.fuel.gasoline.averageConsumption.rural)
                tvConsumptionMixed.text = doubleFormat.format(it.fuel.gasoline.averageConsumption.mixed)
                tvCO2Emission.text = doubleFormat.format(it.emission.gasoline.co2.mixed)
            }
        )
    }

    /*
     * Private Methods
     */

    private fun loadCarData() {
        disposables.add(
            carViewModel.getCar().subscribe({
                tvError.visibility = View.GONE
                clInfo.visibility = View.VISIBLE
            }, {
                tvError.visibility = View.VISIBLE
                clInfo.visibility = View.GONE
                Observable.timer(5, TimeUnit.SECONDS).subscribe { loadCarData() }
            })
        )
    }
}
