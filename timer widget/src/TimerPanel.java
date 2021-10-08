import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TimerPanel extends JPanel implements Runnable {
    JLabel timer;
    JLabel progressBar;
    JButton startButton;

    int progress;
    int mm, ss, hs;

    TimerPanel() {
        Color purple = new Color(118, 51, 227);
        Color lightPurple = new Color(220, 200, 255);

        Font timerFont = new Font("HelvLight", Font.PLAIN, 60);

        setPreferredSize(new Dimension(300, 150));
        setLayout(null);
        setOpaque(false);

        progressBar = new JLabel();
        progressBar.setFont(timerFont);
        progressBar.setBackground(lightPurple);
        progressBar.setBounds(0, 0, 300, 150);
        progressBar.setVerticalAlignment(JLabel.CENTER);
        progressBar.setHorizontalAlignment(JLabel.CENTER);
        progressBar.setVerticalTextPosition(JLabel.CENTER);
        progressBar.setHorizontalTextPosition(JLabel.CENTER);
        progressBar.setOpaque(true);

        timer = new JLabel("00:00.00");
        timer.setFont(timerFont);
        timer.setForeground(purple);
        timer.setBounds(25, 40, 250, 70);
        timer.setVerticalAlignment(JLabel.CENTER);
        timer.setHorizontalAlignment(JLabel.CENTER);
        timer.setVerticalTextPosition(JLabel.CENTER);
        timer.setHorizontalTextPosition(JLabel.CENTER);

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
        startButton.addActionListener(e -> {
            startButton.setVisible(false);
            mm = Integer.parseInt(JOptionPane.showInputDialog("Value in Minutes"));
            progress = 150;
            new Thread(this).start();
        });
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
        add(startButton);
        add(timer);
        add(progressBar);
    }

    @Override
    public void run() {
        mm = mm - 1;
        ss = 59;
        hs = 99;
        while (mm >= 0) {
            while (ss >= 0) {
                while (hs >= 0) {
                    hs--;
                    String timerText = String.format("%02d", mm) + ":"
                            + String.format("%02d", ss) + "."
                            + String.format("%02d", hs);
                    timer.setText(timerText);
                    progressBar.setBounds(0, 150 - progress, 300, progress);
                    progressBar.repaint();
                    try {
                        //noinspection BusyWait
                        Thread.sleep(10);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    if (ss == 0 && mm == 0) {
                        progress--;
                    }
                }
                if (mm == 0) {
                    progress--;
                }
                ss--;
                hs = 99;
            }
            progress--;
            mm--;
            ss = 59;
        }
        startButton.setVisible(true);
        System.out.println("Time's Up!");
        timer.setText("00:00.00");
    }
}

