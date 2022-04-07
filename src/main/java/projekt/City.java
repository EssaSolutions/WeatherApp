package projekt;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class City {
    String name;
    ArrayList<SingleDay> days;
    String currency;

    String weather;
    int hour;
    String weatherPL;
    String key = "155f76b65fa0f6d8645d3e7df760d4c3";

    String currKey = "9a72b13a97661276ff93a9b5";
    String inputCurrency;
    String locCurrency;

    Map<String, String> waluty = new HashMap<String, String>() {
        {
            put("PL", "PLN");
            put("US", "USD");
            put("GB", "GBP");
            put("ES", "EUR");
            put("FR", "EUR");
            put("DE", "EUR");
            put("CA", "CAD");

        }
    };

    public City(String name, String currency) {
        this.name = name;
        inputCurrency = currency;
        this.days = new ArrayList<SingleDay>();
        try {
            createList();
        } catch (UnirestException | IOException u) {
            u.printStackTrace();
        }
    }

    public ArrayList<SingleDay> getDays() {
        return days;
    }




    private void createList() throws UnirestException, IOException {
        String response = Unirest.get("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&appid=" + key)
                .asJson()
                .getBody()
                .toString();
        JSONObject json = new JSONObject(response);

        String responsePL = Unirest.get("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&lang=pl&appid=" + key)
                .asJson()
                .getBody()
                .toString();
        JSONObject jsonPL = new JSONObject(responsePL);

        long timezone = json.getJSONObject("city").getLong("timezone");
        weather = json.getJSONArray("list").getJSONObject(0).getJSONArray("weather").getJSONObject(0).getString("main");


        String countryCode = json.getJSONObject("city").getString("country");
        System.out.println(countryCode);
        String localCurrency = waluty.get(countryCode);
        locCurrency = localCurrency;
        System.out.println(localCurrency);
        String url_str = "https://api.exchangerate.host/convert?from="+ localCurrency + "&to="+inputCurrency;

        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        String req_result = jsonobj.get("result").getAsString();
        System.out.println(req_result);


        String url_str2 = "https://api.exchangerate.host/convert?from=PLN&to="+inputCurrency;

        URL url2 = new URL(url_str2);
        HttpURLConnection request2 = (HttpURLConnection) url2.openConnection();
        request.connect();

        JsonParser jp2 = new JsonParser();
        JsonElement root2 = jp2.parse(new InputStreamReader((InputStream) request2.getContent()));
        JsonObject jsonobj2 = root2.getAsJsonObject();

        String req_result2 = jsonobj2.get("result").getAsString();
        System.out.println(req_result2);

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
            //int pressure = object.getJSONObject("main").getInt("pressure");
            //int humidity = object.getJSONObject("main").getInt("humidity");
            Double pressure = Double.valueOf(req_result);
            Double humidity = Double.valueOf(req_result2);
            days.add(new SingleDay(calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH), temperature, calendar.get(Calendar.HOUR_OF_DAY), weatherr, description, pressure, humidity,weatherPL));
            System.out.println(weatherr);
        }

    }

    public String checkWeather() {
        return weather;
    }


    public String nameToString(){ return name.replace("+", " "); }
}