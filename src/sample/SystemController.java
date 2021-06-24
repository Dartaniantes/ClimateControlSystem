package sample;

import sample.model.HumidityController;
import sample.model.TemperatureController;

public class SystemController {
    private TemperatureController tempCont;
    private HumidityController humCont;

    public SystemController() {
        tempCont = new TemperatureController(22);
        humCont = new HumidityController(40);
    }

    public int getTemperature() {
        return tempCont.getTemperature();
    }
    /*


    * */

    public void setTemperature(int temperature) {
        tempCont.setTemperature(temperature);
    }

    public void setHumidity(int humidity) {
        humCont.setHumidity(humidity);
    }

    public int getHumidity() {
        return humCont.getHumidity();
    }

    public int incrementTemperature() {
        return tempCont.incrementTemperature();
    }

    public int decrementTemperature() {
        return tempCont.decrementTemperature();
    }

    public int decrementHumidity() {
        return humCont.decrementHumidity();
    }

    public int incrementHumidity() {
        return humCont.incrementHumidity();
    }

}
