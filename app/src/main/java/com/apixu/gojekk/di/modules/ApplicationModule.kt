package com.apixu.gojekk.di.modules

import dagger.Module

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module(includes = [NetworkModule::class])
abstract class ApplicationModule {

}
