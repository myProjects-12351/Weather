package app;

public class ObjectController {
    private static ObjectController instance;
    private Weather targetWeather;

    private ObjectController() {
        // Domyślna inicjalizacja
        targetWeather = new Weather(
                "Kielce",
                "PL",
                "20.0",  // temperaturaValue
                "25.0",  // temperatureMaxValue
                "15.0",  // temperatureMinValue
                "20.0",  // temperatureFeelsValue
                "Celsius",  // temperatureUnit
                "60",  // humidityValue
                "%",  // humidityUnit
                "1013",  // pressureValue
                "hPa",  // pressureUnit
                "5",  // windSpeed
                "m/s",  // windSpeedUnit
                "Moderate",  // windName
                "270",  // windDirection
                "20",  // cloudValue
                "%",  // cloudName
                "Clear",  // skyValue
                "10000",  // visibilityValue
                "No precipitation",  // precipitationMode
                "2024-01-23 12:00:00"  // lastUpdate
        );
    }

    public static synchronized ObjectController getInstance() {
        if (instance == null) {
            instance = new ObjectController();
        }
        return instance;
    }

    public Weather getTargetWeather() {
        return targetWeather;
    }

    public void setTargetWeather(Weather targetWeather) {
        this.targetWeather = targetWeather;
    }

}
