import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Frame extends JFrame implements ItemListener {
    // clock, timer container
    JPanel panel;

    // dark-light mode button
    JCheckBox themeCheckBox;

    // timer components labels
    JLabel hoursCounter;
    JLabel minutesCounter;
    JLabel secondsCounter;

    JLabel hoursLabel;
    JLabel minutesLabel;
    JLabel secondsLabel;

    JLabel dateLabelCounter;
    JLabel lineSeparatorLabel;

    // timer values
    String hoursNow;
    String minutesNow;
    String secondsNow;
    String today;

    // date formatter
    DateTimeFormatter formatter;

    // images, icons used
    Image clockLogo;
    ImageIcon darkMode;
    ImageIcon lightMode;

    // clock component dimensions
    Dimension clockDimension;

    // clock color palette
    Color myLightBlue;
    Color myLightPink;
    Color myDarkBlue;
    Color myDarkPink;
    Color colorWheel;
    Color lightSkyLineBlue;
    Color darkSkyLineBlue;
    Color oceanBlue;
    Color metalBlue;

    // color constant
    int R, G, B, round;

    // clock fonts
    Font timerFont;
    Font todayFont;
    Font timeLabelFont;

    Frame() {
        myLightBlue = new Color(45, 155, 245);
        myLightPink = new Color(255, 0, 106);
        myDarkBlue = new Color(20, 126, 212);
        myDarkPink = new Color(230, 0, 100);
        clockDimension = new Dimension(300, 150);
        clockLogo = new ImageIcon("clock.png").getImage();

        this.setTitle("DIGITAL CLOCK");
        this.setIconImage(clockLogo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.BLACK);
        this.setSize(clockDimension);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);


//        ---------------------------------------- THEME BUTTON ------------------------------------------
        lightMode = new ImageIcon("lightMode.png");
        darkMode = new ImageIcon("darkMode.png");

        themeCheckBox = new JCheckBox();
        themeCheckBox.setIcon(lightMode);
        themeCheckBox.setSelectedIcon(darkMode);
        themeCheckBox.setOpaque(false);
        themeCheckBox.setBounds(0, 0, 30, 24);
        themeCheckBox.setMnemonic(KeyEvent.VK_0); // keyboard shortcut for theme change
        themeCheckBox.setFocusable(false);
        themeCheckBox.addItemListener(this);


//        ----------------------------------------------TIMER----------------------------------------------

        // DARK THEME COLOR
        myLightBlue = new Color(45, 155, 245);
        myLightPink = new Color(255, 0, 106);
        myDarkBlue = new Color(20, 126, 212);
        myDarkPink = new Color(230, 0, 100);

        // LIGHT THEME COLOR
        lightSkyLineBlue = new Color(177, 210, 218);
        darkSkyLineBlue = new Color(129, 175, 190);
        metalBlue = new Color(69, 139, 159);
        oceanBlue = new Color(0, 0, 100);

        todayFont = new Font("poppins", Font.PLAIN, 24);
        timerFont = new Font("poppins", Font.PLAIN, 40);
        timeLabelFont = new Font("poppins", Font.PLAIN, 12);

        formatter = DateTimeFormatter.ofPattern("EEE dd, MMM yyyy");

        R = G = B = 0;
        round = 1;


        // ----- TODAY LABEL -----
        dateLabelCounter = new JLabel(today);
        dateLabelCounter.setBounds(50, 10, 200, 30);
        dateLabelCounter.setForeground(Color.WHITE);
        dateLabelCounter.setFont(todayFont);
        dateLabelCounter.setHorizontalAlignment(JLabel.CENTER);
        dateLabelCounter.setVerticalAlignment(JLabel.CENTER);


        // SEPARATOR LABEL
        lineSeparatorLabel = new JLabel("........................................");
        lineSeparatorLabel.setBounds(50, 30, 200, 15);
        lineSeparatorLabel.setForeground(colorWheel);
        lineSeparatorLabel.setFont(todayFont);
        lineSeparatorLabel.setHorizontalAlignment(JLabel.CENTER);
        lineSeparatorLabel.setVerticalAlignment(JLabel.CENTER);


        // ----- HH:MM:SS TIMER -----
        hoursCounter = new JLabel(hoursNow);
        hoursCounter.setBounds(50, 60, 60, 60);
        hoursCounter.setBackground(myLightBlue);
        hoursCounter.setForeground(Color.WHITE);
        hoursCounter.setFont(timerFont);
        hoursCounter.setHorizontalAlignment(JLabel.CENTER);
        hoursCounter.setVerticalAlignment(JLabel.CENTER);
        hoursCounter.setOpaque(true);

        minutesCounter = new JLabel(minutesNow);
        minutesCounter.setBounds(120, 60, 60, 60);
        minutesCounter.setBackground(myLightBlue);
        minutesCounter.setForeground(Color.WHITE);
        minutesCounter.setFont(timerFont);
        minutesCounter.setHorizontalAlignment(JLabel.CENTER);
        minutesCounter.setVerticalAlignment(JLabel.CENTER);
        minutesCounter.setOpaque(true);

        secondsCounter = new JLabel(secondsNow);
        secondsCounter.setBounds(190, 60, 60, 60);
        secondsCounter.setBackground(myLightPink);
        secondsCounter.setForeground(Color.WHITE);
        secondsCounter.setFont(timerFont);
        secondsCounter.setHorizontalAlignment(JLabel.CENTER);
        secondsCounter.setVerticalAlignment(JLabel.CENTER);
        secondsCounter.setOpaque(true);


        // ----- HR, MIN, SEC LABEL -----
        hoursLabel = new JLabel("HOURS");
        hoursLabel.setBounds(50, 120, 60, 20);
        hoursLabel.setBackground(myDarkBlue);
        hoursLabel.setForeground(Color.WHITE);
        hoursLabel.setFont(timeLabelFont);
        hoursLabel.setHorizontalAlignment(JLabel.CENTER);
        hoursLabel.setVerticalAlignment(JLabel.CENTER);
        hoursLabel.setOpaque(true);

        minutesLabel = new JLabel("MINUTES");
        minutesLabel.setBounds(120, 120, 60, 20);
        minutesLabel.setBackground(myDarkBlue);
        minutesLabel.setForeground(Color.WHITE);
        minutesLabel.setFont(timeLabelFont);
        minutesLabel.setHorizontalAlignment(JLabel.CENTER);
        minutesLabel.setVerticalAlignment(JLabel.CENTER);
        minutesLabel.setOpaque(true);

        secondsLabel = new JLabel("SECONDS");
        secondsLabel.setBounds(190, 120, 60, 20);
        secondsLabel.setBackground(myDarkPink);
        secondsLabel.setForeground(Color.WHITE);
        secondsLabel.setFont(timeLabelFont);
        secondsLabel.setHorizontalAlignment(JLabel.CENTER);
        secondsLabel.setVerticalAlignment(JLabel.CENTER);
        secondsLabel.setOpaque(true);
//        ----------------------------------------------/TIMER---------------------------------------------


//        CONTAINER PANEL
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(clockDimension);
        panel.setBackground(Color.BLACK);

        panel.add(themeCheckBox);
        panel.add(dateLabelCounter);
        panel.add(lineSeparatorLabel);
        panel.add(hoursCounter);
        panel.add(minutesCounter);
        panel.add(secondsCounter);
        panel.add(hoursLabel);
        panel.add(hoursLabel);
        panel.add(minutesLabel);
        panel.add(secondsLabel);


        this.add(panel);
        this.pack();
        this.setVisible(true);

        setDateTime();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.getContentPane().setBackground(lightSkyLineBlue);
            panel.setBackground(lightSkyLineBlue);
            hoursCounter.setBackground(metalBlue);
            minutesCounter.setBackground(metalBlue);
            secondsCounter.setBackground(metalBlue);
            hoursLabel.setBackground(darkSkyLineBlue);
            minutesLabel.setBackground(darkSkyLineBlue);
            secondsLabel.setBackground(darkSkyLineBlue);

            dateLabelCounter.setForeground(oceanBlue);
            hoursCounter.setForeground(oceanBlue);
            minutesCounter.setForeground(oceanBlue);
            secondsCounter.setForeground(oceanBlue);
            hoursLabel.setForeground(oceanBlue);
            minutesLabel.setForeground(oceanBlue);
            secondsLabel.setForeground(oceanBlue);

            System.out.println("CHANGED TO THE LIGHT THEME, SUCCESSFULLY");
        } else {
            this.getContentPane().setBackground(Color.BLACK);
            panel.setBackground(Color.BLACK);
            hoursCounter.setBackground(myLightBlue);
            minutesCounter.setBackground(myLightBlue);
            secondsCounter.setBackground(myLightPink);
            hoursLabel.setBackground(myDarkBlue);
            minutesLabel.setBackground(myDarkBlue);
            secondsLabel.setBackground(myDarkPink);

            dateLabelCounter.setForeground(Color.white);
            hoursCounter.setForeground(Color.white);
            minutesCounter.setForeground(Color.white);
            secondsCounter.setForeground(Color.white);
            hoursLabel.setForeground(Color.white);
            minutesLabel.setForeground(Color.white);
            secondsLabel.setForeground(Color.white);

            System.out.println("CHANGED TO THE DARK THEME, SUCCESSFULLY");
        }
    }

    @SuppressWarnings({"BusyWait", "InfiniteLoopStatement"})
    public void setDateTime() {
        while (true) {
            LocalDateTime dateTime = LocalDateTime.now();
            today = dateTime.format(formatter);
            dateLabelCounter.setText(today);

            hoursNow = String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
            hoursCounter.setText(hoursNow);

            minutesNow = String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
            minutesCounter.setText(minutesNow);

            secondsNow = String.valueOf(Calendar.getInstance().get(Calendar.SECOND));
            secondsCounter.setText(secondsNow);

            setRGBValue();

            System.out.println(R + "," + G + "," + B);
            colorWheel = new Color(R, G, B);
            lineSeparatorLabel.setForeground(colorWheel);

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRGBValue() {
        switch (round) {
            case 1 -> {
                if (R < 255) {
                    R += 1;
                } else if (G < 255) {
                    G += 1;
                } else if (B < 255) {
                    B += 1;
                } else {
                    R = B = G = 0;
                    round = 2;
                }
            }
            case 2 -> {
                if (R < 255) {
                    R += 1;
                } else if (B < 255) {
                    B += 1;
                } else if (G < 255) {
                    G += 1;
                } else {
                    R = B = G = 0;
                    round = 3;
                }
            }
            case 3 -> {
                if (G < 255) {
                    G += 1;
                } else if (R < 255) {
                    R += 1;
                } else if (B < 255) {
                    B += 1;
                } else {
                    R = B = G = 0;
                    round = 4;
                }
            }
            case 4 -> {
                if (G < 255) {
                    G += 1;
                } else if (B < 255) {
                    B += 1;
                } else if (R < 255) {
                    R += 1;
                } else {
                    R = B = G = 0;
                    round = 5;
                }
            }
            case 5 -> {
                if (B < 255) {
                    B += 1;
                } else if (G < 255) {
                    G += 1;
                } else if (R < 255) {
                    R += 1;
                } else {
                    R = B = G = 0;
                    round = 6;
                }
            }
            case 6 -> {
                if (B < 255) {
                    B += 1;
                } else if (R < 255) {
                    R += 1;
                } else if (G < 255) {
                    G += 1;
                } else {
                    R = B = G = 0;
                    round = 1;
                }
            }
        }
    }
}
