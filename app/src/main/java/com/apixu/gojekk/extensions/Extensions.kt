package com.apixu.gojekk.extensions

import android.view.ViewGroup
import java.text.SimpleDateFormat

/**
 *
Created by PRince Midha on 30/01/19.
 */

/** Get children of a [ViewGroup] */
val ViewGroup.children: List<*>
    get() = (0 until childCount).map { getChildAt(it) }

/** Check [Collection] for null or empty */
fun Collection<*>?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

/** Check [Collection] for null or empty */
fun Collection<*>?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/** Check [String] for null or empty */
fun String?.isNotNullOrEmpty(): Boolean {
    return this != null && this.isNotEmpty()
}

/** Check [String] for null or empty */
fun String?.isNullOrEmpty(): Boolean {
    return this == null || this.isEmpty()
}

/**
 * "let"s when the collection is non-null and non-empty
 * */
fun <T : Collection<*>> T?.letEmpty(f: (it: T) -> Unit) {
    if (this != null && this.isNotEmpty()) f(this)
}

fun String.convertDate2Day() : String{
    val format = SimpleDateFormat("yyyy-MM-dd")
    val dt = format.parse(this)
    val format2 = SimpleDateFormat("EEEE")
    return format2.format(dt)
}
