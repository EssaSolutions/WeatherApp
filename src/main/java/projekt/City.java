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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class City {
    String name;
    ArrayList<SingleDay> days;

    boolean isWindy;
    boolean isRainy;
    boolean isSunny;
    boolean isCloudy;
    String weather;
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
        long timezone = Integer.toUnsignedLong(json.getJSONObject("city").getInt("timezone"));

        weather = json.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");
        checkWeather();
        System.out.println(weather);

        JSONArray list = json.getJSONArray("list");
        for (int i = 0; i < list.length(); i++) {
            JSONObject object = list.getJSONObject(i);
            double temp = object.getJSONObject("main").getDouble("temp");
            int temperature = (int) (temp - 273.15);
            long time = Integer.toUnsignedLong(object.getInt("dt"));
            Date date = new Date((time - timezone) * 1000);
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(date);

//            String weatherr = object.getJSONArray("weather").getJSONObject(0).getString("main");
//            //System.out.println(weatherr);

            days.add(new SingleDay(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), temperature, calendar.get(Calendar.HOUR_OF_DAY)));
        }

    }

    public void checkWeather()
    {
        if (weather == "Clouds")
        {
            isCloudy = true;
        }
        if (weather == "Sunny")
        {
            isSunny = true;
        }
        if (weather == "Rainy")
        {
            isRainy = true;
        }
        if (weather == "Windy")
        {
            isWindy = true;
        }

//        Image image = new Image(new File("puppy.gif").toURI().toString());
//        ImageView imageview = new ImageView(image);

//        githubIcon = new SVGPath();
//        githubIcon.setContent("M47.7,35.4     c0-4.6-3.7-8.2-8.2-8.2c-1,0-1.9,0.2-2.8,0.5c-0.3-3.4-3.1-6.2-6.6-6.2c-3.7,0-6.7,3-6.7,6.7c0,0.8,0.2,1.6,0.4,2.3     c-0.3-0.1-0.7-0.1-1-0.1c-3.7,0-6.7,3-6.7,6.7c0,3.6,2.9,6.6,6.5,6.7l17.2,0C44.2,43.3,47.7,39.8,47.7,35.4z");
//        githubIcon.setFill(Color.web("#91C0F8"));
        //contentContainer.getChildren().add(githubIcon);


    }
}