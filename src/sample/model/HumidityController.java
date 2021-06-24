package sample.model;

public class HumidityController {
    private int humidity;
    public static final int MIN_HUMIDITY = 10;
    public static final int MAX_HUMIDITY = 100;

    public HumidityController(int humidity) {
        this.humidity = humidity;
    }

    public int incrementHumidity() {
        setHumidity(++this.humidity);
        return this.humidity;
    }
    public int decrementHumidity() {
        setHumidity(--this.humidity);
        return this.humidity;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        if (humidity < MIN_HUMIDITY)
            throw new RuntimeException("MINIMUM HUMIDITY LIMIT IS REACHED");
        if(humidity > MAX_HUMIDITY)
            throw new RuntimeException("MAXIMUM HUMIDITY LIMIT IS REACHED");
        this.humidity = humidity;
    }
}
