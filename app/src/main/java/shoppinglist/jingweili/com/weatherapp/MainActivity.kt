package shoppinglist.jingweili.com.weatherapp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_cell.view.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var cityNames = arrayListOf("Rochester", "New York", "Boca Raton")
    var cities = ArrayList<WeatherForCity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityNames.forEach { WeatherFetcher().execute(WeatherAPI.currentWeatherEndpoint(it)) }
    }

    // ListView Adapter
    inner class WeatherForCityAdapter(var listOfCities: ArrayList<WeatherForCity>) : BaseAdapter() {

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val city = listOfCities[p0]
            val weatherCell = layoutInflater.inflate(R.layout.weather_cell, null)
            weatherCell.cityNameTextView.text = city.name
            weatherCell.tempTextView.text = city.temperature.toString()
            //val currentTime = LocalDateTime.now().format(DateTimeFormatter.ISO_TIME)
            //weatherCell.timeTextView.text = currentTime
            weatherCell.weatherConditionTextView.text = city.condition
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

    // HTTP Calls
    inner class WeatherFetcher : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg p0: String?): String {
            try {
                val url = URL(p0[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout = 7000
                publishProgress(WeatherAPI.convertStreamToString(urlConnect.inputStream))
            } catch (ex: Exception) {

            }
            return ""
        }

        override fun onPreExecute() {
            super.onPreExecute()
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                if (values != null) {
                    cities.add(WeatherForCity(values[0]!!))
                    if (cities.size == cityNames.size) {
                        weatherListView.adapter = WeatherForCityAdapter(cities)
                    }
                }
            } catch (ex: Exception) {

            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }
}
