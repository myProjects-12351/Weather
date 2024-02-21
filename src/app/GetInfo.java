package app;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;


public class GetInfo {
    public void getInfo(String city) throws IOException {
        Weather weather;
        ObjectController objectController;

        String link = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=e3d7be86c31e80aaf771fd4321bac8d1&units=metric&mode=xml&lang=pl&exclude=current";
        URL url = new URL(link);

        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        if(connection.getResponseCode() != 200){
            throw  new RuntimeException("HTTPS request code: " + connection.getResponseCode());
        }else {
            System.out.println("UDALO SIE");

            StringBuilder stringBuilder = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());

            while(scanner.hasNext()){
                stringBuilder.append(scanner.nextLine());
            }
            scanner.close();
            String xmlString = stringBuilder.toString();
            System.out.println(xmlString);

            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

                XPathFactory xPathFactory = XPathFactory.newInstance();
                XPath xPath = xPathFactory.newXPath();

                // Przykład pobierania informacji
                // PLACE
                String cityName = getNodeValue(document, xPath, "/current/city/@name");
                String countryValue = getNodeValue(document, xPath, "/current/city/country/text()");
                //TEMPERATURE
                String temperatureValue = getNodeValue(document, xPath, "/current/temperature/@value");
                String temperatureMaxValue = getNodeValue(document, xPath, "/current/temperature/@max");
                String temperatureMinValue = getNodeValue(document, xPath, "/current/temperature/@min");
                String temperatureFeelsValue = getNodeValue(document, xPath, "/current/feels_like/@value");
                String temperatureUnit = getNodeValue(document, xPath, "/current/temperature/@unit");
                //HUMIDITY
                String humidityValue = getNodeValue(document, xPath, "/current/humidity/@value");
                String humidityUnit = getNodeValue(document, xPath, "/current/humidity/@unit");
                //PRESURE
                String pressureValue = getNodeValue(document, xPath, "/current/pressure/@value");
                String pressureUnit = getNodeValue(document, xPath, "/current/pressure/@unit");
                //WIND
                String windSpeed = getNodeValue(document, xPath, "/current/wind/speed/@value");
                String windSpeedUnit = getNodeValue(document, xPath, "/current/wind/speed/@unit");
                String windName = getNodeValue(document, xPath, "/current/wind/speed/@name");
                String windDirection = getNodeValue(document, xPath, "/current/wind/direction/@name");
                //CLOUDS
                String cloudValue = getNodeValue(document, xPath, "/current/clouds/@value");
                String cloudName = getNodeValue(document, xPath, "/current/clouds/@name");
                String skyValue = getNodeValue(document, xPath, "/current/weather/@value");
                //VISIBILITY
                String visibilityValue = getNodeValue(document, xPath, "/current/visibility/@value");
                //PRACIPITATION
                String precipitationMode = getNodeValue(document, xPath, "/current/precipitation/@mode");
                //LAST UPDATE
                String lastUpdate = getNodeValue(document, xPath, "/current/lastupdate/@value");

                weather = new Weather(cityName, countryValue, temperatureValue, temperatureMaxValue, temperatureMinValue, temperatureFeelsValue, temperatureUnit, humidityValue, humidityUnit, pressureValue, pressureUnit, windSpeed, windSpeedUnit, windName, windDirection, cloudValue, cloudName, skyValue, visibilityValue, precipitationMode, lastUpdate);

                objectController = ObjectController.getInstance();
                objectController.setTargetWeather(weather);

                weather.print();

                System.out.println("ZAPISANO");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String getNodeValue(Document document, XPath xPath, String expression) throws XPathExpressionException {
        XPathExpression xPathExpression = xPath.compile(expression);
        Node node = (Node) xPathExpression.evaluate(document, XPathConstants.NODE);
        return node != null ? node.getNodeValue() : null;
    }
}
