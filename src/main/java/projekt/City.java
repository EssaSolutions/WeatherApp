package projekt;


import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class City {
    String name;
    ArrayList<SingleDay> days;

    String weather;
    int hour;
    String weatherPL;
    String key = "155f76b65fa0f6d8645d3e7df760d4c3";

    public City(String name) {
        this.name = name;
        this.days = new ArrayList<SingleDay>();
        try {
            createList();
        } catch (UnirestException u) {
            u.printStackTrace();
        }
    }

    public ArrayList<SingleDay> getDays() {
        return days;
    }


    private void createList() throws UnirestException {
        String response = Unirest.get("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&appid=" + key)
                .asJson()
                .getBody()
                .toString();
        JSONObject json = new JSONObject(response);

        String responsePL = Unirest.get("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&lang=pl&appid=155f76b65fa0f6d8645d3e7df760d4c3")
                .asJson()
                .getBody()
                .toString();
        JSONObject jsonPL = new JSONObject(responsePL);

        long timezone = json.getJSONObject("city").getLong("timezone");
        weather = json.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");

        checkWeather();


        JSONArray list = json.getJSONArray("list");
        for (int i = 0; i < list.length(); i++) {

            JSONObject object = list.getJSONObject(i);
            weatherPL = jsonPL.getJSONArray("list").getJSONObject(i).getJSONArray("weather").getJSONObject(0).getString("description");
            double temp = object.getJSONObject("main").getDouble("temp");
            int temperature = (int) (temp - 273.15);
            long time = object.getLong("dt");
            Date date = new Date((time + timezone) * 1000);
            Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTime(date);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            String weatherr = object.getJSONArray("weather").getJSONObject(0).getString("main");
            String description = object.getJSONArray("weather").getJSONObject(0).getString("description");
            int pressure = object.getJSONObject("main").getInt("pressure");
            int humidity = object.getJSONObject("main").getInt("humidity");
            days.add(new SingleDay(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), temperature, calendar.get(Calendar.HOUR_OF_DAY), weatherr, description, pressure, humidity,weatherPL));
        }

    }

    public String checkWeather() {
        return weather;
    }

    public String nameToString(){ return name.replace("+", " "); }
}