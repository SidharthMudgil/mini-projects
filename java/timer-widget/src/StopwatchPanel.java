import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StopwatchPanel extends JPanel implements ActionListener, ItemListener, Runnable {
    JLabel counter;

    JButton startButton;
    JCheckBox playPauseButton;
    JButton lapButton;
    JButton stopButton;

    JFrame lapsFrame;
    JTextPane lapsPane;

    int mm, ss, hs, totalLaps;
    boolean on;

    StopwatchPanel() {
        Color purple = new Color(118, 51, 227);
        Color lightPurple = new Color(180, 147, 236);

        Font counterFont = new Font("HelvLight", Font.PLAIN, 60);
        Font lapFont = new Font("poppins", Font.PLAIN, 18);

        ImageIcon playIcon = new ImageIcon("play.png");
        ImageIcon pauseIcon = new ImageIcon("pause.png");
        ImageIcon stopIcon = new ImageIcon("stop.png");
        ImageIcon lapIcon = new ImageIcon("lap.png");

        on = false;
        totalLaps = 0;

        setPreferredSize(new Dimension(300, 150));
        setLayout(null);
        setOpaque(false);

        counter = new JLabel("00:00.00");
        counter.setFont(counterFont);
        counter.setForeground(purple);
        counter.setBounds(25, 35, 250, 70);
        counter.setVerticalAlignment(JLabel.CENTER);
        counter.setHorizontalAlignment(JLabel.CENTER);
        counter.setVerticalTextPosition(JLabel.CENTER);
        counter.setHorizontalTextPosition(JLabel.CENTER);

        playPauseButton = new JCheckBox();
        playPauseButton.setBounds(160, 110, 30, 30);
        playPauseButton.setVerticalAlignment(JCheckBox.CENTER);
        playPauseButton.setHorizontalAlignment(JCheckBox.CENTER);
        playPauseButton.setBorderPainted(false);
        playPauseButton.setContentAreaFilled(false);
        playPauseButton.setIcon(playIcon);
        playPauseButton.setSelectedIcon(pauseIcon);
        playPauseButton.setSelected(true);
        playPauseButton.setVisible(false);
        playPauseButton.addItemListener(this);

        stopButton = new JButton();
        stopButton.setBounds(100, 110, 30, 30);
        stopButton.setVerticalAlignment(JButton.CENTER);
        stopButton.setHorizontalAlignment(JButton.CENTER);
        stopButton.setBorderPainted(false);
        stopButton.setContentAreaFilled(false);
        stopButton.setIcon(stopIcon);
        stopButton.setVisible(false);
        stopButton.addActionListener(this);

        lapButton = new JButton();
        lapButton.setBounds(100, 110, 30, 30);
        lapButton.setVerticalAlignment(JButton.CENTER);
        lapButton.setHorizontalAlignment(JButton.CENTER);
        lapButton.setFont(lapFont);
        lapButton.setBorderPainted(false);
        lapButton.setContentAreaFilled(false);
        lapButton.setIcon(lapIcon);
        lapButton.setVisible(false);
        lapButton.addActionListener(this);

        lapsPane = new JTextPane();
        lapsPane.setPreferredSize(new Dimension(70, 150));
        lapsPane.setBackground(lightPurple);
        lapsPane.setForeground(Color.WHITE);
        lapsPane.setEditable(false);


        lapsFrame = new JFrame();
        lapsFrame.setUndecorated(true);
        lapsFrame.setBackground(new Color(0, 0, 0, 0));
        lapsFrame.setSize(70, 150);
        lapsFrame.setLayout(new FlowLayout());
        lapsFrame.add(lapsPane);
        lapsFrame.pack();


        startButton = new JButton("start");
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(lightPurple);
        startButton.setBounds(110, 110, 80, 30);
        startButton.setVerticalAlignment(JButton.CENTER);
        startButton.setHorizontalAlignment(JButton.CENTER);
        startButton.setVerticalTextPosition(JButton.CENTER);
        startButton.setHorizontalTextPosition(JButton.CENTER);
        startButton.setBorder(null);
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(purple);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton) e.getSource()).setBackground(lightPurple);
            }
        });

        add(playPauseButton);
        add(stopButton);
        add(lapButton);
        add(startButton);
        add(counter);

        new Thread(this).start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            System.out.println("START button pressed");
            playPauseButton.setVisible(true);
            lapButton.setVisible(true);
            startButton.setVisible(false);
            on = true;
            new Thread(this).start();
        }
        if (e.getSource() == stopButton) {
            System.out.println("EVENT #STOP SUCCESSFUL");
            stopButton.setVisible(false);
            playPauseButton.setVisible(false);
            lapsFrame.dispose();
            startButton.setVisible(true);
            mm = ss = hs = 0;
            counter.setText("00:00.00");
            lapsPane.setText("");
            on = false;
        }
        if (e.getSource() == lapButton) {
            System.out.println("EVENT #LAP_N SUCCESSFUL");
            totalLaps++;
            lapsFrame.setVisible(true);
            if (totalLaps <= 9) {
                lapsPane.setText(lapsPane.getText() + "+" + counter.getText() + "\n");
            } else {
                lapsPane.setText("");
                lapsPane.setText("+" + counter.getText() + "\n");
                totalLaps = 1;
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == playPauseButton) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("EVENT #PLAY SUCCESSFUL");
                stopButton.setVisible(false);
                lapButton.setVisible(true);
                on = true;
                new Thread(this).start();
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                System.out.println("EVENT #PAUSE SUCCESSFUL");
                lapButton.setVisible(false);
                stopButton.setVisible(true);
                on = false;
            }
        }
    }

    @Override
    public void run() {
        while (on) {
            String counterText = String.format("%02d", mm) + ":"
                    + String.format("%02d", ss) + "."
                    + String.format("%02d", hs);
            if (hs == 99) {
                ss++;
                hs = 0;
            } else if (ss == 59) {
                mm++;
                ss = 0;
            }
            hs++;
            counter.setText(counterText);
            try {
                //noinspection BusyWait
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
