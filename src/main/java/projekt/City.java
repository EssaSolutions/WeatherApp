package projekt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.codec.binary.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class City{
    String name;
    ArrayList<SingleDay> days;

    public City(String name){
        this.name = name;
        this.days = new ArrayList<SingleDay>();
        try {
            createList();
        } catch(UnirestException u){
            u.printStackTrace();
        }
    }

    public ArrayList<SingleDay> getDays(){
        return days;
    }

    public int size(){
        return days.size();
    }

    private void createList() throws UnirestException {
        String response = Unirest.get("http://api.openweathermap.org/data/2.5/forecast?q=" + name + "&appid=155f76b65fa0f6d8645d3e7df760d4c3")
                .asJson()
                .getBody()
                .toString();
        JSONObject json = new JSONObject(response);
        int zone = json.getJSONObject("city").getInt("timezone")/3600;
        JSONArray list = json.getJSONArray("list");
        for(int i = 0; i < list.length(); i++)
        {
            JSONObject object = list.getJSONObject(i);
            String date = object.getString("dt_txt");
            String[] dates = date.split(" ");
            int hour = Integer.valueOf(dates[1].substring(0, 2));
            int month = Integer.valueOf(dates[0].substring(5, 7));
            int day = Integer.valueOf(dates[0].substring(8));
            hour += zone;
            if (hour < 0)
            {

                hour +=24;
                day -= 1;
                if (day == 0)
                {
                    month -=1;
                    day = 30;
                }

            }
            else if (hour > 24)
            {
                hour -=24;
                day +=1;
                if (day == 31)
                {
                    month +=1;
                    day = 1;
                }
            }
            double temp = object.getJSONObject("main").getDouble("temp");
            int temperature = (int)(temp - 273.15);
            days.add(new SingleDay(month, day, temperature, hour));
        }
    }
}