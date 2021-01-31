


public class Main {
    public static void main(String[] args) throws Exception {
        
        StentParam params = new StentParam();
        params.setOuterDiameter(3);
        params.setWallThickness(0.5);
        params.setLength(15);
        params.setMeshAngle(40);
        params.setMeshWidth(0.6);
        params.setRingHeight(2);
        params.setMeshPillarCount(4);
        params.setName("artTest1");
        Slicer slicer = Slicer.getInstance();
        slicer.generateStent(params);
        
        //DisplayWindow window = DisplayWindow.getInstance();
        // Window window = new Window();

        // double num = 0;
        // while(window.graphic != null) {
        //     num += 0.1;
        //     window.graphic.update((int)num);
        // }

        // JFrame window = new JFrame("arGen");
        // RendPanel panel = RendPanel.getInstance();

        // window.setContentPane(panel);
        // window.getContentPane().addMouseListener(ClickListener.getInstance(panel));
        // window.setSize(Constants.WINDOW_SIZE_X, Constants.WINDOW_SIZE_Y);
        // window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // window.setVisible(true);
    }

    
}


