package sample.model;

public class TemperatureController {
    private int temperature;
    public static final int MAX_TEMPERATURE = 40;
    public static final int MIN_TEMPERATURE = 5;

    public TemperatureController(int temperature) {
        this.temperature = temperature;
    }

    public int getTemperature() {
        return temperature;
    }

    public int incrementTemperature() {
        setTemperature(++this.temperature);
        return this.temperature;
    }

    public int decrementTemperature() {
        setTemperature(--this.temperature);
        return this.temperature;
    }

    public void setTemperature(int temperature) {
        if (temperature < MIN_TEMPERATURE) {
            throw new RuntimeException("MINIMUM TEMPERATURE LIMIT IS REACHED");
        }

        if(temperature > MAX_TEMPERATURE)
            throw new RuntimeException("MAXIMUM TEMPERATURE LIMIT IS REACHED");

        this.temperature = temperature;
    }
}
