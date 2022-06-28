import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CloseButton extends JButton {
    CloseButton() {
        ImageIcon closeUnfocusedIcon = new ImageIcon("close-unfocused.png");
        ImageIcon closeFocusedIcon = new ImageIcon("close-focused.png");
        
        setIcon(closeUnfocusedIcon);
        setBounds(280, 2, 18, 18);
        setPreferredSize(new Dimension(18, 18));
        setVerticalAlignment(JButton.CENTER);
        setHorizontalAlignment(JButton.CENTER);
        setFocusable(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(closeFocusedIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(closeUnfocusedIcon);
            }
        });
        addActionListener(e -> {
                System.out.println("SYSTEM SHUTTING OFF...");
                System.exit(0);
        });
    }
}
