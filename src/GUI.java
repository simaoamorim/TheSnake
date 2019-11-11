import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;

class GUI extends JFrame {
    private Grid grid;
    private JSlider slider;
    private JPanel panel2;
    private static final Dimension frameSize = new Dimension(600,600);
    private static final Dimension maxFrameSize = Toolkit.getDefaultToolkit().getScreenSize();
    private JButton startButton;
    private JButton resetButton;
    private Timer iterationTimer;
    private static final int timeStep = 100; // Time in ms

    GUI() {
        setSize(frameSize);
        setMinimumSize(new Dimension(200, 200));
        setMaximumSize(maxFrameSize);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initUI();
        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void initUI() {
        setLayout(new BorderLayout());
        grid = new Grid();
        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panel2.add(grid);
        JScrollPane mainPanel = new JScrollPane(panel2);
        add(mainPanel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        resetButton = new JButton("Reset");
        resetButton.setActionCommand("reset");
        resetButton.addActionListener(this::buttonActions);
        startButton = new JButton("Start");
        startButton.setActionCommand("start");
        startButton.addActionListener(this::buttonActions);
        slider = new JSlider(JSlider.HORIZONTAL);
        slider.setMinimum(1);
        slider.setMaximum(50);
        slider.setValue(10);
        slider.addChangeListener(this::zoomChosen);
        resetButton.setSize(100,60);
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(startButton);
        buttonsPanel.add(resetButton);
        buttonsPanel.add(new JSeparator(JSeparator.HORIZONTAL));
        buttonsPanel.add(new JLabel("Zoom:"));
        buttonsPanel.add(slider);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void buttonActions(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "reset" : {
                slider.setValue(10);
                pack(); // Resize the window to fit contents
                setLocationRelativeTo(null); // Move window to the center of the screen
                break;
            }
            default: {
                break;
            }
        }
    }

    private void zoomChosen(ChangeEvent e) {
        int reqSize = slider.getValue();
        System.out.println(String.format("Setting zoom to %d", reqSize));
        grid.setCellSize(reqSize);
        grid.repaint();
        grid.revalidate();
        panel2.setPreferredSize(new Dimension((int)grid.getPreferredSize().getWidth()+5, (int)grid.getPreferredSize().getHeight()+5));
    }

}
