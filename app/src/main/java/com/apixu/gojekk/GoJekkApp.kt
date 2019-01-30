package com.apixu.gojekk

import android.app.Activity
import android.app.Application
import com.apixu.gojekk.di.DaggerInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by PRince Midha on 30/01/19.
 */
class GoJekkApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): DispatchingAndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        DaggerInjector.injectAll(this)
    }
}
