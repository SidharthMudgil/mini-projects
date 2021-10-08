import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame implements ActionListener {
    int posX = 0;
    int posY = 0;
    JPanel mainPanel;
    ClockPanel clockPanel;
    StopwatchPanel stopwatchPanel;
    TimerPanel timerPanel;

    CloseButton closeButton;
    DragArea dragArea;
    SwipeButton rightSwipe;
    SwipeButton leftSwipe;

    int speed;

    Frame() {
        speed = 10;

        closeButton = new CloseButton();

        dragArea = new DragArea();
        dragArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                posX = e.getX();
                posY = e.getY();
            }
        });
        dragArea.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //sets frame position when mouse dragged
                setLocation(e.getXOnScreen() - posX, e.getYOnScreen() - posY);
                stopwatchPanel.lapsFrame.setLocation(e.getXOnScreen() - posX + 300, e.getYOnScreen() - posY);
            }
        });

        leftSwipe = new SwipeButton("left");
        rightSwipe = new SwipeButton("right");
        leftSwipe.addActionListener(this);
        rightSwipe.addActionListener(this);
    }

    void createFrame() {
        //clockPanel
        clockPanel = new ClockPanel();
        clockPanel.setBounds(0, 0, 300, 150);
        stopwatchPanel = new StopwatchPanel();
        stopwatchPanel.setBounds(300, 0, 300, 150);
        timerPanel = new TimerPanel();
        timerPanel.setBounds(600, 0, 300, 150);


        //mainPanel
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 300, 150);
        mainPanel.setPreferredSize(new Dimension(300, 150));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(null);
        mainPanel.add(dragArea);
        mainPanel.add(closeButton);
        mainPanel.add(leftSwipe);
        mainPanel.add(rightSwipe);
        mainPanel.add(clockPanel);
        mainPanel.add(stopwatchPanel);
        mainPanel.add(timerPanel);

        //frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(400, 400);
        setLayout(new FlowLayout());
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        add(mainPanel);
        pack();
        setVisible(true);

        clockPanel.setTime();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == leftSwipe) {
            new Timer(0, e1 -> {
                leftSwipe.setVisible(false);
                clockPanel.setLocation(clockPanel.getX() + speed, 0);
                stopwatchPanel.setLocation(stopwatchPanel.getX() + speed, 0);
                timerPanel.setLocation(timerPanel.getX() + speed, 0);
                if (clockPanel.getX() == 0) {
                    leftSwipe.setEnabled(false);
                    leftSwipe.setVisible(true);
                    ((Timer) e1.getSource()).stop();
                } else if (clockPanel.getX() == -300) {
                    rightSwipe.setEnabled(true);
                    leftSwipe.setVisible(true);
                    ((Timer) e1.getSource()).stop();
                }
            }).start();
        }
        if (e.getSource() == rightSwipe) {
            new Timer(0, e1 -> {
                rightSwipe.setVisible(false);
                clockPanel.setLocation(clockPanel.getX() - speed, 0);
                stopwatchPanel.setLocation(stopwatchPanel.getX() - speed, 0);
                timerPanel.setLocation(timerPanel.getX() - speed, 0);

                if (clockPanel.getX() == -300) {
                    leftSwipe.setEnabled(true);
                    rightSwipe.setVisible(true);
                    ((Timer) e1.getSource()).stop();
                } else if (clockPanel.getX() == -600) {
                    rightSwipe.setEnabled(false);
                    rightSwipe.setVisible(true);
                    ((Timer) e1.getSource()).stop();
                }
            }).start();
        }
    }
}
