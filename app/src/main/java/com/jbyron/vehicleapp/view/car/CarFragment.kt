package com.jbyron.vehicleapp.view.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jbyron.vehicleapp.App
import com.jbyron.vehicleapp.R
import com.jbyron.vehicleapp.constant.NetworkState
import com.jbyron.vehicleapp.view.BaseFragment
import com.jbyron.vehicleapp.viewmodel.CarViewModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_car.*
import timber.log.Timber
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
                Timber.i("Success")
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

                tvError.visibility = View.GONE
                pbLoading.visibility = View.GONE
                clInfo.visibility = View.VISIBLE
            },

            carViewModel.state.subscribe {
                when (it) {
                    NetworkState.ERROR -> {
                        Timber.i("NetworkState ERROR")
                        tvError.visibility = View.VISIBLE
                        pbLoading.visibility = View.GONE
                        clInfo.visibility = View.GONE
                        Observable.timer(5, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { loadCarData() }
                    }
                    NetworkState.LOADING -> {
                        Timber.i("NetworkState LOADING")
                        tvError.visibility = View.GONE
                        pbLoading.visibility = View.VISIBLE
                        clInfo.visibility = View.GONE
                    }
                }
            }
        )
    }

    /*
     * Private Methods
     */

    private fun loadCarData() {
        disposables.add(
            carViewModel.getCar().subscribe({}, {
                Timber.e(it)
            })
        )
    }
}
