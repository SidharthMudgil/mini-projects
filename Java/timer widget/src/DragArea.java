import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragArea extends JLabel {
    DragArea() {
        ImageIcon dragIcon = new ImageIcon("drag-indicator.png");
        setBounds(0, -2, 24, 18);
        setPreferredSize(new Dimension(24, 18));
        setVerticalAlignment(JLabel.TOP);
        setHorizontalAlignment(JLabel.LEFT);
        setVisible(true);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(dragIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(null);
            }
        });
    }
}
