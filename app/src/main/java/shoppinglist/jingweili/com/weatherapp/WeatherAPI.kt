package shoppinglist.jingweili.com.weatherapp

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class WeatherAPI {
    companion object {
        private val apiKey = "8db4c641b056f50183017d16021b08f5"


        /* e.g. api.openweathermap.org/data/2.5/weather?q=Troy,us&APPID=8db4c641b056f50183017d16021b08f5\
         * Must have a https prefix
         */
        fun currentWeatherEndpoint(city: String, country: String = "us"): String {
            return "https://api.openweathermap.org/data/2.5/weather?q=$city,$country&APPID=$apiKey"
        }

        fun convertStreamToString(inputStream: InputStream): String {
            return inputStream.bufferedReader().use { it.readText() }
        }
    }
}