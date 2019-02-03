package com.apixu.gojekk.extensions

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment


/**
 *Created by PRince Midha on 30/01/19.
 */
inline fun <reified VM : ViewModel, T> T.viewModel(): Lazy<VM> where T : Fragment {
    return lazy { ViewModelProviders.of(this).get(VM::class.java) }
}
