package shoppinglist.jingweili.com.weatherapp

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

class DetailedWeatherAdapter(protected val dataSet: ArrayList<WeatherForCity>):
        RecyclerView.Adapter<DetailedWeatherAdapter.DetailedWeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): DetailedWeatherViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: DetailedWeatherViewHolder?, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class DetailedWeatherViewHolder: RecyclerView.ViewHolder() {

    }
}