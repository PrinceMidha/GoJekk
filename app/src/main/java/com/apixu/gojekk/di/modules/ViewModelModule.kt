package com.apixu.gojekk.di.modules


import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.apixu.gojekk.di.ViewModelKey
import com.apixu.gojekk.livedata.ViewModelFactory
import com.apixu.gojekk.ui.forecast.ForecastViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForecastViewModel::class)
    internal abstract fun bindRepoViewModel(repoViewModel: ForecastViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
