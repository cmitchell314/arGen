import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.*;
import javax.swing.*;

public class RendPanel extends JPanel {
    
    private static RendPanel rendPanel;
    private double stentDiameter;
    private double stentThickness;
    private double stentColumnCount;
    private double stentLength;

    private double stentDiameterInput;
    private double stentThicknessInput;
    private double stentLengthInput;
    private double stentColumnWidthInput;

    private JTextField diameterField, lengthField;

    private RendPanel() {
        this.setLayout(null);
        diameterField = new JTextField(10);
        // diameterField.setHorizontalAlignment(100);
        //diameterField.setBounds(Constants.WINDOW_SIZE_X - 160, Constants.WINDOW_SIZE_Y - 80, 100, 20);
        diameterField.setLocation(10, 30);

        lengthField = new JTextField(10);
        lengthField.setLocation(5, 5);
        
        this.add(diameterField);
        this.add(lengthField);
    }

    public static RendPanel getInstance() {
        if (rendPanel == null) {
            rendPanel = new RendPanel();
        } 
        return rendPanel;
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.setBackground(Color.DARK_GRAY);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.RED);
        g2d.fillRect(Constants.WINDOW_SIZE_X - 170, Constants.WINDOW_SIZE_Y - 80, 150, 50);
        g2d.fillRect(Constants.WINDOW_SIZE_X - 170, Constants.WINDOW_SIZE_Y - 150, 150, 50);
        g2d.fillRect(Constants.WINDOW_SIZE_X - 170, Constants.WINDOW_SIZE_Y - 220, 150, 50);
        g2d.fillRect(Constants.WINDOW_SIZE_X - 170, Constants.WINDOW_SIZE_Y - 290, 150, 50);
        g2d.fillRect(Constants.WINDOW_SIZE_X - 170, Constants.WINDOW_SIZE_Y - 360, 150, 50);
        g2d.fillRect(Constants.WINDOW_SIZE_X - 170, Constants.WINDOW_SIZE_Y - 430, 150, 50);
        g2d.setFont(new Font("Roboto", Font.ITALIC, 10));
        g2d.setColor(Color.WHITE);
        g2d.drawString("Diameter", Constants.WINDOW_SIZE_X - 170, Constants.WINDOW_SIZE_Y - 60);


    }


}
