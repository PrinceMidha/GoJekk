package com.apixu.gojekk.di

import android.app.Application
import com.apixu.gojekk.GoJekkApp
import com.apixu.gojekk.di.modules.ApplicationModule
import com.apixu.gojekk.di.modules.MainActivityModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by PRince Midha on 30/01/19.
 */

@Singleton
@Component(modules = [ApplicationModule::class, AndroidInjectionModule::class, MainActivityModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: GoJekkApp)
}
