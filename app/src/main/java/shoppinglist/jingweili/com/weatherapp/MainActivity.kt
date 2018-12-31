package shoppinglist.jingweili.com.weatherapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_cell.view.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    var cities: ArrayList<WeatherForCity> = arrayListOf(
            WeatherForCity("Rochester", 37),
            WeatherForCity("NYC", 52))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherListView.adapter = WeatherForCityAdapter(cities)
    }

    inner class WeatherForCityAdapter(var listOfCities: ArrayList<WeatherForCity>) : BaseAdapter() {

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val city = listOfCities[p0]
            val weatherCell = layoutInflater.inflate(R.layout.weather_cell, null)
            weatherCell.cityNameTextView.text = city.name
            weatherCell.tempTextView.text = city.temperature.toString()
            val currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME)
            weatherCell.timeTextView.text = currentTime
            return weatherCell
        }

        override fun getItem(p0: Int): Any {
            return cities[p0]
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return cities.size
        }

    }
}
