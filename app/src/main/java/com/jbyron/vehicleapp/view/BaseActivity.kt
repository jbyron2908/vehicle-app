package com.jbyron.vehicleapp.view

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.jbyron.vehicleapp.R
import com.jbyron.vehicleapp.util.FragmentUtil
import timber.log.Timber

open class BaseActivity : AppCompatActivity() {
    override fun onBackPressed() {
        val alertDialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.app_name))
            .setMessage(getString(R.string.leave_message))
            .setPositiveButton(getString(R.string.leave_yes)) { _, _ -> finish() }
            .setNegativeButton(getString(R.string.leave_no)) { _, _ -> Timber.i("I changed my mind...") }
            .setCancelable(false)
            .create()

        if (FragmentUtil.getFragments(this).size == 1)
            alertDialog.show()
        else
            FragmentUtil.pop(this)
    }
}