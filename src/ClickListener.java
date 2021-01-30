import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import java.awt.Color;

public class ClickListener extends MouseAdapter {
    
    private static ClickListener clickListener = null;
    private RendPanel panel;
    private boolean isClicked = false;

    private ClickListener(RendPanel panel) {
        super();
        this.panel = panel;
    }

    static public ClickListener getInstance(RendPanel panel) {
        if (clickListener == null) {
            clickListener = new ClickListener(panel);
        }

        return clickListener;
    }

    @Override 
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        isClicked = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isClicked = false;
    }

    public boolean getPressed() {
        return isClicked;
    }
}
