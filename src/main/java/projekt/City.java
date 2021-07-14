package projekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.*;

public class City {
    String name;
    ArrayList<SingleDay> days;

    boolean isWindy;
    boolean isRainy;
    boolean isSunny;
    boolean isCloudy;
    String weather;
    int hour;
    //static SVGPath githubIcon;

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

    public int size() {
        return days.size();
    }

    private void createList() throws UnirestException {
        String response = Unirest.get("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&appid=155f76b65fa0f6d8645d3e7df760d4c3")
                .asJson()
                .getBody()
                .toString();
        JSONObject json = new JSONObject(response);
        long timezone = json.getJSONObject("city").getLong("timezone");
        weather = json.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");
        checkWeather();

        JSONArray list = json.getJSONArray("list");
        for (int i = 0; i < list.length(); i++) {
            JSONObject object = list.getJSONObject(i);
            double temp = object.getJSONObject("main").getDouble("temp");
            int temperature = (int) (temp - 273.15);
            long time = object.getLong("dt");
            Date date = new Date((time + timezone) * 1000);
            Calendar calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
            calendar.setTime(date);
            hour = calendar.get(Calendar.HOUR_OF_DAY);

            String weatherr = object.getJSONArray("weather").getJSONObject(0).getString("main");
            JSONObject weatherrr = object.getJSONArray("weather").getJSONObject(0);
            String description = object.getJSONArray("weather").getJSONObject(0).getString("description");
            int pressure = object.getJSONObject("main").getInt("pressure");
            int humidity = object.getJSONObject("main").getInt("humidity");
            days.add(new SingleDay(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), temperature, calendar.get(Calendar.HOUR_OF_DAY), weatherr, description, pressure, humidity));
        }

    }

    public String checkWeather() {
        return weather;
    }

    public int checkHour() {
        return hour;
    }

    public String getName() {
        return name;
    }

    public String nameToString(){ return name.replace("+", " "); }
}