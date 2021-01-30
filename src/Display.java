
import java.awt.*;
import javax.swing.*;


public class Display extends JPanel {
    
    Graphics g2D;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.GRAY);
        g2D = (Graphics2D) g;
    }

    public void update(int x) {
        this.setBackground(Color.GRAY);
        g2D.setColor(Color.DARK_GRAY);
        g2D.fillRect(x, 50, 400, 200);
    }
}

//Graphics2D g2D = (Graphics2D) g;

        // g2D.setColor(Color.green);
        // g2D.setStroke(new BasicStroke(2));
        // g2D.drawPolyline(xPoints, yPoints, nPoints);

        // g2D.setFont(new Font("Times New Roman", Font.ITALIC, 25));
        // g2D.drawString("wat", 100, 100);

        // g2D.setColor(Color.RED);
        // g2D.setStroke(new BasicStroke(20));
        // g2D.drawLine(100, 100, 400, 400);

        // int x[] = {100, 200, 300};
        // int y[] = {100, 400, 100};
        // g2D.setColor(Color.gray);
        // g2D.setStroke(new BasicStroke(5));
        // g2D.fillPolygon(x, y, 3);

        // g2D.drawRect(50, 50, 300, 200);
        // g2D.fillRect(100, 100, 200, 100);
        
        // g2D.drawOval(50, 50, 300, 300);

        // g2D.setPaint(new Color(150, 250, 150));
        // g2D.drawArc(100, 100, 100, 200, 0, -180);

        // GradientPaint paint = new GradientPaint(0, 0, Color.RED, 500, 500, Color.GRAY);

        // g2D.setPaint(paint);
        // g2D.fillOval(50, 50, 400, 400);
