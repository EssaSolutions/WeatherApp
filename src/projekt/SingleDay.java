package projekt;

public class SingleDay {

    private int month;
    private int day;
    private int temperature;

    public SingleDay(int month, int day, int temperature){
        this.month = month;
        this.day = day;
        this.temperature = temperature;
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
}
