package app;

public class Weather {
    private final String cityName;
    private final String countryValue;
    private final String temperatureValue;
    private final String temperatureMaxValue;
    private final String temperatureMinValue;
    private final String temperatureFeelsValue;
    private final String temperatureUnit;
    private final String humidityValue;
    private final String humidityUnit;
    private final String pressureValue;
    private final String pressureUnit;
    private final String windSpeed;
    private final String windSpeedUnit;
    private final String windName;
    private final String windDirection;
    private final String cloudValue;
    private final String cloudName;
    private final String skyValue;
    private final String visibilityValue;
    private final String precipitationMode;
    private final String lastUpdate;

    public Weather(String cityName, String countryValue, String temperatureValue, String temperatureMaxValue, String temperatureMinValue, String temperatureFeelsValue, String temperatureUnit, String humidityValue, String humidityUnit, String pressureValue, String pressureUnit, String windSpeed, String windSpeedUnit, String windName, String windDirection, String cloudValue, String cloudName, String skyValue, String visibilityValue, String precipitationMode, String lastUpdate) {
        this.cityName = cityName;
        this.countryValue = countryValue;
        this.temperatureValue = temperatureValue;
        this.temperatureMaxValue = temperatureMaxValue;
        this.temperatureMinValue = temperatureMinValue;
        this.temperatureFeelsValue = temperatureFeelsValue;
        this.temperatureUnit = temperatureUnit;
        this.humidityValue = humidityValue;
        this.humidityUnit = humidityUnit;
        this.pressureValue = pressureValue;
        this.pressureUnit = pressureUnit;
        this.windSpeed = windSpeed;
        this.windSpeedUnit = windSpeedUnit;
        this.windName = windName;
        this.windDirection = windDirection;
        this.cloudValue = cloudValue;
        this.cloudName = cloudName;
        this.skyValue = skyValue;
        this.visibilityValue = visibilityValue;
        this.precipitationMode = precipitationMode;
        this.lastUpdate = lastUpdate;
    }

    public void print(){
        // Wyświetl pobrane informacje
        System.out.println("PLACE:");
        System.out.println("City Name: " + cityName);
        System.out.println("Country: " + countryValue);

        System.out.println("\nTEMPERATURE:");
        System.out.println("Temperature: " + temperatureValue);
        System.out.println("Max Temperature: " + temperatureMaxValue);
        System.out.println("Min Temperature: " + temperatureMinValue);
        System.out.println("Feels Like Temperature: " + temperatureFeelsValue);
        System.out.println("Temperature Unit: " + temperatureUnit);

        System.out.println("\nHUMIDITY:");
        System.out.println("Humidity: " + humidityValue);
        System.out.println("Humidity Unit: " + humidityUnit);

        System.out.println("\nPRESURE:");
        System.out.println("Pressure: " + pressureValue);
        System.out.println("Pressure Unit: " + pressureUnit);

        System.out.println("\nWIND:");
        System.out.println("Wind Speed: " + windSpeed);
        System.out.println("Wind Speed Unit: " + windSpeedUnit);
        System.out.println("Wind Name: " + windName);
        System.out.println("Wind Direction: " + windDirection);

        System.out.println("\nCLOUDS:");
        System.out.println("Cloud Value: " + cloudValue);
        System.out.println("Cloud Name: " + cloudName);
        System.out.println("Sky Value: " + skyValue);

        System.out.println("\nVISIBILITY:");
        System.out.println("Visibility: " + visibilityValue);

        System.out.println("\nPRACIPITATION:");
        System.out.println("Precipitation Mode: " + precipitationMode);

        System.out.println("\nLAST UPDATE:");
        System.out.println("Last Update: " + lastUpdate);
    }

    public String getCountryValue() {
        return countryValue;
    }

    public String getTemperatureValue() {
        return temperatureValue;
    }

    public String getTemperatureMaxValue() {
        return temperatureMaxValue;
    }

    public String getTemperatureMinValue() {
        return temperatureMinValue;
    }

    public String getTemperatureFeelsValue() {
        return temperatureFeelsValue;
    }

    public String getTemperatureUnit() {
        String unit = null;
        if(this.temperatureUnit.equals("celsius"))
            unit = "°C";
        else if (this.temperatureUnit.equals("kelvin")) {
            unit = "K";
        } else if (this.temperatureUnit.equals("fahrenheit")) {
            unit = "F";
        }
        return unit;
    }

    public String getHumidityValue() {
        return humidityValue;
    }

    public String getHumidityUnit() {
        return humidityUnit;
    }

    public String getPressureValue() {
        return pressureValue;
    }

    public String getPressureUnit() {
        return pressureUnit;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getWindSpeedUnit() {
        return windSpeedUnit;
    }

    public String getWindName() {
        return windName;
    }

    public String getWindDirection() {
        if(windDirection == null)
            return "BRAK";
        return windDirection;
    }

    public String getCloudValue() {
        return cloudValue;
    }

    public String getCloudName() {
        return cloudName;
    }

    public String getSkyValue() {
        return skyValue;
    }

    public String getVisibilityValue() {
        return visibilityValue;
    }

    public String getPrecipitationMode() {
        return precipitationMode;
    }

    public String getLastUpdateDay() {
        return lastUpdate.substring(0,10);
    }

    public String getLastUpdateTime(){
        return lastUpdate.substring(11, lastUpdate.length());
    }

    public String getCityName(){
        return this.cityName;
    }
}
