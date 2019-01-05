package shoppinglist.jingweili.com.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.weather_detail_cell.view.*

class DetailedWeatherAdapter(private val data: WeatherForCity):
        RecyclerView.Adapter<DetailedWeatherAdapter.DetailedWeatherViewHolder>() {

    inner class DetailedWeatherViewHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailedWeatherViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.weather_detail_cell, parent, false)
        return DetailedWeatherViewHolder(view)
    }

    override fun getItemCount(): Int = 7

    override fun onBindViewHolder(holder: DetailedWeatherViewHolder?, position: Int) {
        holder?.view?.weatherName?.text = WeatherForCity.infoToDisplay[position]
        holder?.view?.weatherDetail?.text = dataFor(position, data)
    }

    private fun dataFor(position: Int, weather: WeatherForCity): String {
        return when (position) {
            0 -> weather.name
            1 -> weather.temperature.toString() + " F"
            2 -> weather.condition
            3 -> weather.visibility
            4 -> weather.windSpd
            5 -> weather.humidity
            6 -> weather.pressure
            else -> ""
        }
    }
}