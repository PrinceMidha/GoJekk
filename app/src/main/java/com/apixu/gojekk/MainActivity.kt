package com.apixu.gojekk

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.apixu.gojekk.di.Injectable
import com.apixu.gojekk.extensions.observeK
import com.apixu.gojekk.model.Resource
import com.apixu.gojekk.model.Status
import com.apixu.gojekk.model.forecast.WeatherResponse
import com.apixu.gojekk.ui.error.ErrorFragment
import com.apixu.gojekk.ui.forecast.ForecastFragment
import com.apixu.gojekk.ui.forecast.ForecastViewModel
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: ForecastViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ForecastViewModel::class.java)
        viewModel.forecastResponse.observeK(this, ::onForecastResponse)
        fetchDetails()
    }

    fun fetchDetails() {
        startLoading()
        viewModel.getWeatherForecast()
    }

    private fun onForecastResponse(it: Resource<WeatherResponse>?) {
        when (it?.status) {
            Status.SUCCESS -> {
                var forecast = ForecastFragment()
                var bundle = Bundle()
                bundle.putSerializable(Constants.FORECAST_BUNDLE,it?.data)
                forecast.arguments = bundle
                replaceFragment(forecast)
                clearLoader()
            }

            Status.ERROR -> {
                clearLoader()
                replaceFragment(ErrorFragment())
            }
        }
        Log.d("Response", it?.status.toString() ?: "")
    }

    private fun clearLoader() {
        loader.clearAnimation()
        loader.visibility = View.GONE
    }

    private fun startLoading() {
        loader.visibility = View.VISIBLE
        loader.startAnimation(
            AnimationUtils.loadAnimation(this, R.anim.rotation)
        )
    }

//    fun setFragment(fragment: Fragment) {
//        supportFragmentManager.beginTransaction()
//            .add(R.id.fragmentContainer, fragment, "TEST")
//            .commit()
//    }

    fun replaceFragment(fragment: Fragment) {
        if (supportFragmentManager.backStackEntryCount < 1) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .commit()
        }
    }

    override fun supportFragmentInjector(): DispatchingAndroidInjector<Fragment> {
        return dispatchingAndroidInjector
    }
}
