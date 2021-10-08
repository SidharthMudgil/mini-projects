import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class ClockPanel extends JPanel {
    JLabel hrsLabel;
    JLabel minLabel;
    JLabel secLabel;

    int hrsNow;
    int minNow;
    int secNow;

    ClockPanel() {
        JLabel colon1;
        JLabel colon2;
        JLabel shadow1;
        JLabel shadow2;
        JLabel shadow3;

        final int D = 70;

        Color purple = new Color(118, 51, 227);
        Color lightPurple = new Color(180, 147, 236);
        Color shadowPurple = new Color(118, 51, 227,40);

        Font digitFont = new Font("HelvLight", Font.PLAIN, 38);
        Font colonFont = new Font("anton", Font.PLAIN, 36);

        setPreferredSize(new Dimension(300, 150));
        setLayout(null);
        setOpaque(false);

        hrsLabel = new JLabel("00");
        hrsLabel.setBounds(25, 40, D, D);
        hrsLabel.setVerticalAlignment(JLabel.CENTER);
        hrsLabel.setHorizontalAlignment(JLabel.CENTER);
        hrsLabel.setBackground(purple);
        hrsLabel.setForeground(Color.WHITE);
        hrsLabel.setFont(digitFont);
        hrsLabel.setOpaque(true);

        minLabel = new JLabel("00");
        minLabel.setBounds(115, 40, D, D);
        minLabel.setVerticalAlignment(JLabel.CENTER);
        minLabel.setHorizontalAlignment(JLabel.CENTER);
        minLabel.setBackground(purple);
        minLabel.setForeground(Color.WHITE);
        minLabel.setFont(digitFont);
        minLabel.setOpaque(true);

        secLabel = new JLabel("00");
        secLabel.setBounds(205, 40, D, D);
        secLabel.setVerticalAlignment(JLabel.CENTER);
        secLabel.setHorizontalAlignment(JLabel.CENTER);
        secLabel.setBackground(purple);
        secLabel.setForeground(Color.WHITE);
        secLabel.setFont(digitFont);
        secLabel.setOpaque(true);

        colon1 = new JLabel(":");
        colon1.setBounds(95, 40, 20, D);
        colon1.setVerticalAlignment(JLabel.CENTER);
        colon1.setHorizontalAlignment(JLabel.CENTER);
        colon1.setForeground(lightPurple);
        colon1.setFont(colonFont);

        colon2 = new JLabel(":");
        colon2.setBounds(185, 40, 20, D);
        colon2.setVerticalAlignment(JLabel.CENTER);
        colon2.setHorizontalAlignment(JLabel.CENTER);
        colon2.setForeground(lightPurple);
        colon2.setFont(colonFont);

        shadow1 = new JLabel();
        shadow1.setBounds(30,45,70,70);
        shadow1.setBackground(shadowPurple);
        shadow1.setOpaque(true);

        shadow2 = new JLabel();
        shadow2.setBounds(120,45,70,70);
        shadow2.setBackground(shadowPurple);
        shadow2.setOpaque(true);

        shadow3 = new JLabel();
        shadow3.setBounds(210,45,70,70);
        shadow3.setBackground(shadowPurple);
        shadow3.setOpaque(true);

        add(shadow1);
        add(shadow2);
        add(shadow3);
        add(hrsLabel);
        add(minLabel);
        add(secLabel);
        add(colon1);
        add(colon2);
    }
    @SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
    public void setTime() {
        while (true) {
            hrsNow = Calendar.getInstance().get(Calendar.HOUR);
            if (hrsNow == 0) {
                hrsNow = 12;
            }
            hrsLabel.setText(String.valueOf(String.format("%02d",hrsNow)));

            minNow = Calendar.getInstance().get(Calendar.MINUTE);
            minLabel.setText(String.valueOf(String.format("%02d",minNow)));

            secNow = Calendar.getInstance().get(Calendar.SECOND);
            secLabel.setText(String.valueOf(String.format("%02d",secNow)));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
