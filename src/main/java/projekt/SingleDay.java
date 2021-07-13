package projekt;

public class SingleDay {

    private int month;
    private int day;
    private int temperature;
    private int hour;
    private String imageURL;
    private String weather;
    private String description;
    private int pressure;
    private int humidity;

    public SingleDay(int month, int day, int temperature, int hour, String weather, String description, int pressure, int humidity)
    {
        this.month = month;
        this.day = day;
        this.temperature = temperature;
        this.hour = hour;
        this.weather = weather;
        this.description = description;
        giveURL();
        this.pressure = pressure;
        this.humidity = humidity;
    }

    private void giveURL()
    {

        if(this.hour > 6 && this.hour < 20)
        {
            if (weather.equals("Clear"))
            {
                this.imageURL = "/projekt/images/sun100.png";
            }
            else if(weather.equals("Rain"))
            {
                this.imageURL = "/projekt/images/cloudyy100.png";
            }
            else if(weather.equals("Clouds"))
            {
                if (description.equals("scattered clouds") || description.equals("few clouds"))
                {
                    this.imageURL = "/projekt/images/sun100.png";
                }
                else if (description.equals("broken clouds"))
                {
                    this.imageURL = "/projekt/images/cloudy100.png";

                }
                else
                {
                    this.imageURL = "/projekt/images/cloud100.png";
                }
            }

        }
        else
            {
                if (weather.equals("Clear"))
                {

                    this.imageURL = "/projekt/images/moon100.png";
                }
                else if(weather.equals("Rain"))
                {

                    this.imageURL = "/projekt/images/rain100.png";
                }
                else if(weather.equals("Clouds"))
                {
                    if (description.equals("scattered clouds") || description.equals("few clouds"))
                    {
                        this.imageURL = "/projekt/images/moon100.png";
                    }
                    else if (description.equals("broken clouds"))
                    {
                        this.imageURL = "/projekt/images/cloudd100.png";
                    }
                    else
                    {
                        this.imageURL = "/projekt/images/cloud100.png";
                    }

                }

            }
    }

    public int getTemperature() {
        return this.temperature;
    }

    public String tempToString() {
        return Integer.toString(temperature) + "°C";
    } //

    public String dateToString() {
        String string = "";
        if (day > 9)
            string += Integer.toString(day) + ".";
        else
            string += "0" + Integer.toString(day) + ".";
        if (month > 9)
            string += Integer.toString(month);
        else
            string += "0" + Integer.toString(month);
        return string;
    }

    public String hourToString() {
        return Integer.toString(hour) + ":00";
    }

    public String toString(){
        return dateToString() + " " + hourToString() + ": " + tempToString();
    }

    public String getImageURL(){
        return this.imageURL;
    }

    public String getWeather() {return weather;}

    public String pressureToString(){return Integer.toString(this.pressure) + "hPa";}

    public String humidityToString(){return Integer.toString(this.humidity) + "%";}
}
