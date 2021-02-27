package projekt;

public class SingleDay {

    private int month;
    private int day;
    private int temperature;
    private int hour;
    private String imageURL;

    public SingleDay(int month, int day, int temperature, int hour){
        this.month = month;
        this.day = day;
        this.temperature = temperature;
        this.hour = hour;
        giveURL();
    }

    public int getTemperature() {
        return this.temperature;
    }

    public String tempToString(){
        return Integer.toString(temperature) + "\u2103";
    }

    public String dateToString(){
        String string = "";
        if(day > 9)
            string += Integer.toString(day) + ".";
        else
            string += "0" + Integer.toString(day) + ".";
        if(month>9)
            string += Integer.toString(month);
        else
            string += "0" + Integer.toString(month);
        return string;
    }

    private void giveURL(){
        if(this.hour > 6 && this.hour < 20){
            this.imageURL = "/images/SunWhite.png";
        } else {
            this.imageURL = "/images/MoonWhite.jpg";
        }
    }

    public String getImageURL(){
        return this.imageURL;
    }

    public String hourToString(){
        return Integer.toString(hour) + ":00";
    }
}
