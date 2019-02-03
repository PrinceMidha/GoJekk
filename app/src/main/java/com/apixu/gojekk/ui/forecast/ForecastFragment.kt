package com.apixu.gojekk.ui.forecast

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.apixu.gojekk.R
import com.apixu.gojekk.adapter.ForcastAdapter
import com.apixu.gojekk.di.Injectable
import com.apixu.gojekk.extensions.observeK
import com.apixu.gojekk.model.Resource
import com.apixu.gojekk.model.forecast.Forecast
import com.apixu.gojekk.model.forecast.WeatherResponse
import kotlinx.android.synthetic.main.weather_details.*
import javax.inject.Inject
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.apixu.gojekk.Constants
import android.support.v7.widget.DividerItemDecoration






/**
 * Created by PRince Midha on 30/01/19.
 */
class ForecastFragment : Fragment(), Injectable {
    var data : WeatherResponse? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        data = arguments?.getSerializable(Constants.FORECAST_BUNDLE) as WeatherResponse?
        return inflater.inflate(R.layout.weather_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentCity.text = data?.location?.name
        currentTemp.text = data?.forecast?.forecastday!![0].day?.maxtempC.toString()+ 0x00B0.toChar()+"C"
        val mLayoutManager = LinearLayoutManager(context)
        recycler.setLayoutManager(mLayoutManager)
        val dividerItemDecoration = DividerItemDecoration(context,
                mLayoutManager.getOrientation())
        recycler.addItemDecoration(dividerItemDecoration)
        recycler.adapter = ForcastAdapter(data)
        recycler.startAnimation(
                AnimationUtils.loadAnimation(activity, R.anim.bottom_up))
    }


}
