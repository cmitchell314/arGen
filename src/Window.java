
import javax.swing.JFrame;

public class Window extends JFrame {

    private static final long serialVersionUID = 1L;
    public Display graphic = new Display();

    public Window() {
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(graphic);
        this.setVisible(true);
    }

}
