package shoppinglist.jingweili.com.weatherapp

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
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
        val temperature = weather!!.temperature
        val name = weather!!.name
        textView.text = "$name's temperature is $temperature F"
    }
}