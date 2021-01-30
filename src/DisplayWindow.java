import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


public class DisplayWindow implements ActionListener {
    private static DisplayWindow displayWindow;

    JFrame frame;
    JPanel leftOptionPanel;
    JLabel iconLabel;
    ImageIcon printerImage;
    Border iconBorder;

    JLabel sDiameter, sLength, sThickness;
    JTextField iDiameter, iLength, iThickness;

    Font standardFont;

    Color accent = new Color(255, 100, 0);

    private DisplayWindow() {
        printerImage = new ImageIcon("at0render.png");
        iconBorder = BorderFactory.createLineBorder(accent, 3);
        standardFont = new Font("Arial", Font.ITALIC, 20);
        frame = new JFrame("arGen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y);
        frame.setResizable(false);
        frame.setVisible(true);

        render();
    }

    public static DisplayWindow getInstance() {
        if (displayWindow == null) {
            displayWindow = new DisplayWindow();
        }
        return displayWindow;
    }

    private void render() {
        iconLabel = new JLabel();
        iconLabel.setText("arGen for ARTHETA-0");
        //iconLabel.setIcon(printerImage);
        iconLabel.setForeground(accent);
        iconLabel.setFont(new Font("Arial", Font.BOLD, 30));
        iconLabel.setBackground(Color.BLACK);
        iconLabel.setOpaque(true);
        iconLabel.setBorder(iconBorder);
        iconLabel.setBounds(0, 0, Constants.WINDOW_SIZE_X, 50);
        // iconLabel.setAlignmentX(JLabel.TOP);
        // iconLabel.setAlignmentY(JLabel.CENTER);

        leftOptionPanel = new JPanel();
        leftOptionPanel.setBackground(Color.BLACK);
        leftOptionPanel.setBounds(0, 50, 200, Constants.WINDOW_SIZE_Y - 50);
        leftOptionPanel.setLayout(new FlowLayout());

        sDiameter = new JLabel();
        sDiameter.setText("Diameter:");
        sDiameter.setFont(standardFont);
        //sDiameter.setBounds(10, 40, 300, 20);
        sDiameter.setForeground(accent);

        sLength = new JLabel();
        sLength.setText("Length:");
        sLength.setFont(standardFont);
        //sLength.setBounds(10, 100, 150, 20);
        sLength.setForeground(accent);
        

        sThickness = new JLabel();
        sThickness.setText("Thickness:");
        sThickness.setFont(standardFont);
        //sThickness.setBounds(10, 160, 150, 20);
        sThickness.setForeground(accent);
        

        JTextField iDiameter = new JTextField();
        iDiameter.setPreferredSize(new Dimension(100, 30));
        //iDiameter.setBounds(100, 100, 200, 40);
        // iDiameter.setFont(new Font("Arial", Font.PLAIN, 10));
        // iDiameter.setBounds(20, 120, 100, 30);
        // iLength = new JTextField();
        // iThickness = new JTextField();

        //leftOptionPanel.add(sDiameter);
        leftOptionPanel.add(iDiameter);
        //leftOptionPanel.add(sLength);
        //leftOptionPanel.add(sThickness);
        //leftOptionPanel.add(iDiameter);
 
        //leftOptionPanel.add(iDiameter);


        frame.setLayout(null);
        frame.add(iconLabel);
        frame.add(leftOptionPanel);
    }

    public boolean update() {
        render();
        frame.repaint();
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

    }
}
