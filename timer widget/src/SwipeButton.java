import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwipeButton extends JButton {
    SwipeButton(String value) {
        ImageIcon leftIcon = new ImageIcon("left.png");
        ImageIcon rightIcon = new ImageIcon("right.png");
        if (value.equalsIgnoreCase("left")) {
            setBounds(2, 68, 10, 16);
            setEnabled(false);
        } else if (value.equalsIgnoreCase("right")) {
            setBounds(288, 68, 10, 16);
        }
        setVerticalAlignment(JButton.CENTER);
        setHorizontalAlignment(JButton.CENTER);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setVisible(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (value.equalsIgnoreCase("left")) {
                    setIcon(leftIcon);
                } else if (value.equalsIgnoreCase("right")) {
                    setIcon(rightIcon);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(null);
            }
        });
    }
}