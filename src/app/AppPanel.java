package app;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class AppPanel extends JPanel {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 1000;
    private Weather currentWeather;
    private ObjectController objectController;
    private JLabel cityNameLabel;
    private BufferedImage image;
    private BufferedImage backgroundImage;
    private String fontStyle = "Arial";
    private Font descriptionFont = new Font(fontStyle, Font.PLAIN, 25);
    private String temperatureSymbol;
    private Color panelsColor = new Color(255,255,255,127);
    private Color elementsBackgroundColor = new Color(255,255,255,0);
    private Color bottomLabelColor = new Color(255,255,255,255);

    AppPanel(){
        objectController = ObjectController.getInstance();
        currentWeather = objectController.getTargetWeather();
        temperatureSymbol = currentWeather.getTemperatureUnit();

        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.cyan);

        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/app/Images/weather/background1.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
        try {
            draw(g2d);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2d) throws IOException {
        removeAll();
        objectController = ObjectController.getInstance();
        currentWeather = objectController.getTargetWeather();

        int arcWidth = 100; // szerokość zaokrąglenia rogów
        int arcHeight = 100; // wysokość zaokrąglenia rogów
        // Top panel
        int x = 50;
        int y = 50;
        int top_panel_WIDTH = WIDTH-2*x;
        int top_panel_HEIGHT = 450;
        cityNameLabel = new JLabel();
        cityNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityNameLabel.setText(currentWeather.getCityName());
        cityNameLabel.setBounds(100,11,400,80);
        cityNameLabel.setFont(new Font("Arial", Font.PLAIN, 75));
        cityNameLabel.setBackground(elementsBackgroundColor);
        cityNameLabel.setOpaque(true);
        this.add(cityNameLabel);

        RoundRectangle2D roundedRectangleTop = new RoundRectangle2D.Float(x, y-150, top_panel_WIDTH, top_panel_HEIGHT, arcWidth, arcHeight);
        g2d.setColor(panelsColor);
        g2d.fill(roundedRectangleTop);
        // CURR TEMPERATURE
        newElement(100, 250, 130, "/app/Images/icons/currentTemp.png", g2d,currentWeather.getTemperatureValue()+ " " + temperatureSymbol);
        // LOCATION
        newElement(320,250,100,"/app/Images/icons/pointer.png", g2d,currentWeather.getCountryValue());
        // CLOUDS KSY VALUE
        newElement(100, 96, 350,"/app/Images/icons/clouds.png", g2d,currentWeather.getSkyValue());
        // CLOUDS CLOUD NAME
        newElement(100, 173,350,"/app/Images/icons/cloudsPair.png", g2d,currentWeather.getCloudName());

        // Left Panel
        x = 50;
        y= 375;
        int y2 = 10;
        int x2 =0;
        int left_panel_WIDTH = 210;
        int left_panel_HEIGHT = 545;
        RoundRectangle2D roundedRectangleLeft = new RoundRectangle2D.Float(x,y,left_panel_WIDTH,left_panel_HEIGHT,arcWidth,arcHeight);
        g2d.setColor(panelsColor);
        g2d.fill(roundedRectangleLeft);
        //MIN TEMPERATURE
        newElement(x+10+x2, y+25+y2, 118, "/app/Images/icons/minTemperature.png", g2d,currentWeather.getTemperatureMinValue() + " " + temperatureSymbol );
        // MAX TEMPERATURE
        newElement(x+x2, y+y2+125, 118, "/app/Images/icons/maxTemperature.png", g2d, currentWeather.getTemperatureMaxValue()+ " " + temperatureSymbol);
        // FEELS TEMPERATURE
        newElement(x+x2+10, y+225+y2,118,"/app/Images/icons/feelsTemperature.png", g2d, currentWeather.getTemperatureFeelsValue()+ " " + temperatureSymbol);
        // HUMIDITY
        newElement(x+x2, y+325+y2,118, "/app/Images/icons/humidity.png", g2d, currentWeather.getHumidityValue() + " " + currentWeather.getHumidityUnit());
        // PRESSURE
        newElement(x+x2, y+425+y2,118,"/app/Images/icons/pressure.png", g2d, currentWeather.getPressureValue() + " " + currentWeather.getPressureUnit());

        // Right Panel TOP
        x = 275;
        y= 375;
        int right_panel_WIDTH = 275;
        int right_panel_HEIGHT = 325;
        RoundRectangle2D roundedRectangleRight = new RoundRectangle2D.Float(x,y,right_panel_WIDTH,right_panel_HEIGHT,arcWidth,arcHeight);
        g2d.setColor(panelsColor);
        g2d.fill(roundedRectangleRight);
        // WIND SPEED
        newElement(x+x2, y+20+y2,180, "/app/Images/icons/windSpeed.png", g2d, currentWeather.getWindSpeed()+ " " + currentWeather.getWindSpeedUnit());
        // WIND DIRECTION
        newElement(x+x2, y+115+y2,180, "/app/Images/icons/windDirection.png", g2d, currentWeather.getWindDirection());
        // VISIBILITY
        newElement(x+5, y+215+y2,180,"/app/Images/icons/visibility.png", g2d, currentWeather.getVisibilityValue() + " m");

        // Right panel bottom
        x = 275;
        y= 715;
        int right_panel_bottom_WIDTH = 275;
        int right_panel_bottom_HEIGHT = 200;
        RoundRectangle2D roundedRectangleBottomRight = new RoundRectangle2D.Float(x,y,right_panel_bottom_WIDTH,right_panel_bottom_HEIGHT,100,100);
        g2d.setColor(panelsColor);
        g2d.fill(roundedRectangleBottomRight);
        // INFO
        newJlabel(x+10,y+25,250,40,0,"Infromracje z dnia: ");
        newJlabel(x+5, y+70, 270,40,0,currentWeather.getLastUpdateDay());
        newJlabel(x+5, y+115, 270,40,0,currentWeather.getLastUpdateTime());

        // Bottom panel
        x = 0;
        y= 935;
        int bottom_panel_WIDTH = WIDTH;
        int bottom_panel_HEIGHT = 120;
        RoundRectangle2D roundedRectangleBottom = new RoundRectangle2D.Float(x,y,bottom_panel_WIDTH,bottom_panel_HEIGHT,arcWidth,arcHeight);
        g2d.setColor(bottomLabelColor);
        g2d.fill(roundedRectangleBottom);
    }

    private void newElement(int x, int y,int width, String imagePath, Graphics2D g2d, String text){
        URL imageURL = getClass().getResource(imagePath);
        if (imageURL != null) {
            try {
                image = ImageIO.read(imageURL);
                g2d.drawImage(image, x, y, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Nie można znaleźć obrazu: " + imagePath);
        }
        newJlabel(x + 80, y, width, 72, 2, text);
    }

    private void newJlabel(int x, int y, int width, int height, int aligment ,String text){
        JLabel label = new JLabel();
        label.setText(text);
        label.setBounds(x,y,width,height);
        label.setFont(descriptionFont);
        label.setHorizontalAlignment(aligment);
        label.setBackground(elementsBackgroundColor);
        label.setOpaque(true);
        this.add(label);
    }
}