package shoppinglist.jingweili.com.weatherapp

import android.content.Intent
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.weather_cell.view.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    var cityNames = arrayListOf("Rochester", "Boca Raton", "New York", "Troy")
    var cities = ArrayList<WeatherForCity>()
    var listViewAdapter: BaseAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getWeatherData()
    }

    // Menu | Action Bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null && item.itemId == R.id.refresh_main_list_view_button) {
            cities.clear()
            getWeatherData()
        }
        return super.onOptionsItemSelected(item)
    }

    // ListView Adapter
    inner class WeatherForCityAdapter(var listOfCities: ArrayList<WeatherForCity>) : BaseAdapter() {

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
            val city = listOfCities[p0]
            val weatherCell = layoutInflater.inflate(R.layout.weather_cell, null)
            weatherCell.cityNameTextView.text = city.name
            val temperature = city.temperature.toString() + " F"
            weatherCell.tempTextView.text = temperature
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

        // called on the UI thread
        override fun onProgressUpdate(vararg values: String?) {
            try {
                if (values != null) {
                    cities.add(WeatherForCity(values[0]!!))
                    if (cities.size == cityNames.size) {
                        if (weatherListView.adapter == null) {
                            listViewAdapter = WeatherForCityAdapter(cities)
                            weatherListView.adapter = listViewAdapter
                            weatherListView.setOnItemClickListener { _, _, position, _ ->
                                val intent = Intent(this@MainActivity, DetailedWeatherActivity::class.java)
                                intent.putExtra(WeatherForCity.intentIdentifier, cities[position])
                                startActivity(intent)
                            }
                        } else {
                            listViewAdapter?.notifyDataSetChanged()
                        }
                    }
                }
            } catch (ex: Exception) {

            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }
    }

    private fun getWeatherData() {
        cityNames.forEach { WeatherFetcher().execute(WeatherAPI.currentWeatherEndpoint(it)) }
    }
}
