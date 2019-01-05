package shoppinglist.jingweili.com.weatherapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_detailed_weather.*

class DetailedWeatherActivity: AppCompatActivity() {
    var weather: WeatherForCity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_weather)
    }

    override fun onStart() {
        super.onStart()
        weather = intent.getSerializableExtra(WeatherForCity.intentIdentifier) as WeatherForCity
        if (weather != null) {
            detailed_weather_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            detailed_weather_recycler.adapter = DetailedWeatherAdapter(weather!!)
        }
    }
}