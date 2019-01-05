package shoppinglist.jingweili.com.weatherapp

import org.json.JSONObject
import java.io.Serializable

class WeatherForCity(json: String) : Serializable {
    var name: String = ""
    var temperature: Int = 0
    var condition: String = ""
    var visibility: String = ""
    var windSpd: String = ""
    var humidity: String = ""
    var pressure: String = ""

    init {
        val jsonObject = JSONObject(json)
        name = jsonObject.getString("name")
        temperature = kelvinToFahrenheit(jsonObject .getJSONObject("main").getDouble("temp").toInt())
        condition = (jsonObject .getJSONArray("weather")[0] as JSONObject).getString("main")
        visibility = (jsonObject.getString("visibility").toInt() / 1609).toString() + " mi"
        windSpd = jsonObject.getJSONObject("wind").getDouble("speed").toString() + " mps"
        humidity = jsonObject.getJSONObject("main").getInt("humidity").toString() + " %"
        pressure = jsonObject.getJSONObject("main").getInt("pressure").toString() + " hPa"
    }

    private fun kelvinToFahrenheit(kelvin: Int): Int {
        return (1.8 * (kelvin - 273) + 32).toInt()
    }

    companion object {
        val intentIdentifier = "WeatherData"
        val infoToDisplay = arrayListOf("City", "Temperature", "Condition", "Visibility", "Wind Speed",
                "Humidity", "Pressure")
    }

}