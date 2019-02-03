package com.apixu.gojekk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.apixu.gojekk.R
import android.widget.TextView
import com.apixu.gojekk.extensions.convertDate2Day
import com.apixu.gojekk.model.forecast.WeatherResponse
import kotlinx.android.synthetic.main.weather_item.view.*
import java.text.SimpleDateFormat


class ForcastAdapter(var data: WeatherResponse?): RecyclerView.Adapter<ForcastAdapter.ForcastAdapterViewHolder>() {
    override fun getItemCount(): Int {
        return data?.forecast?.forecastday?.size?.minus(1) ?: 0
    }

    override fun onBindViewHolder(viewHolder: ForcastAdapterViewHolder, position: Int) {
        viewHolder.day.text = data?.forecast?.forecastday!![position+1].date?.convertDate2Day()
        viewHolder.temprature.text = data?.forecast?.forecastday!![position+1].day?.maxtempC.toString()+" C"
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ForcastAdapterViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.weather_item,null)
        return ForcastAdapterViewHolder(view)
    }

    inner class ForcastAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var day: TextView = view.day
        var temprature: TextView = view.temprature
    }
}