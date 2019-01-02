package shoppinglist.jingweili.com.weatherapp

import org.json.JSONObject


class WeatherForCity {
    var name: String = ""
    var temperature: Int = 0
    var condition: String = ""

    constructor(json: String) {
        val jsonObject = JSONObject(json)
        this.name = jsonObject .getString("name")
        this.temperature = jsonObject .getJSONObject("main").getDouble("temp").toInt()
        this.condition = (jsonObject .getJSONArray("weather")[0] as JSONObject).getString("main")
    }

}