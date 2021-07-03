package projekt;

public class SingleDay {

    private int month;
    private int day;
    private int temperature;
    private int hour;
    private String imageURL;

    public SingleDay(int month, int day, int temperature, int hour) {
        this.month = month;
        this.day = day;
        this.temperature = temperature;
        this.hour = hour;
        giveURL();
    }

    private void giveURL(){
        if(this.hour > 6 && this.hour < 20){
            this.imageURL = "/projekt/images/SunWhite100px.png";
        } else {
            this.imageURL = "/projekt/images/MoonWhite100px.png";
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
}
