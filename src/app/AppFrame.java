package app;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AppFrame extends JFrame implements ActionListener {
    JButton button;
    JTextField textField;
    JPanel panel;
    GetInfo getInfo = new GetInfo();
    String city;
    File file = new File("lastCity.txt");

    int x = 40;
    int y = 950;
    AppFrame() throws IOException {
        boolean succes = file.createNewFile();
        if(succes){
            readToFile("Kielce");
        }

        readFromFile();

        getInfo.getInfo(city);
        panel = new AppPanel();

        textField = new JTextField();
        Border border = BorderFactory.createEmptyBorder(); // Ustaw kolor i grubość obramowania
        textField.setBorder(border);
        textField.setBounds(x,y,400,40);
        textField.setFont(new Font("Arial", Font.PLAIN, 35));
        textField.setBackground(new Color(255,255,255,255));
        textField.addActionListener(this);

        button = new JButton();
        button.setBounds(x+410, y, 100,40);
        button.addActionListener(this);
        button.setText("SUBMIT");
        button.setBackground(new Color(255,255,255,0));
        button.setBorder(border);

        this.add(textField);
        this.add(button);
        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setTitle("POGODYNKA");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button || e.getSource() == textField){
            city = textField.getText();
            try {
                getInfo.getInfo(city);
                readToFile(city);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            repaint();
        }
    }

    private void readFromFile() throws IOException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            city = scanner.nextLine();
            System.out.println("Z pliku: " + city);
        }
    }

    private void readToFile(String text) throws IOException {
        PrintWriter writer = new PrintWriter(file);
        writer.println(text);
        writer.close();
        System.out.println("ZAPISANO W PLIKU");
    }
}
