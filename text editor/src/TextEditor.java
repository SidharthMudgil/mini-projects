import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextEditor extends JFrame implements ActionListener {
    JTextArea textArea;
    JScrollPane scrollPane;
    JLabel fontLabel;
    JSpinner fontSizeSpinner;
    JComboBox<String> fontBox;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;

    TextEditor() {
        ImageIcon icon = new ImageIcon("logo.png");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Text Editor");
        this.setIconImage(icon.getImage());
        this.setSize(900, 600);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);

//        TEXT FIELD
        textArea = new JTextArea();

        textArea.setText("Made By Sidharth Mudgil");
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Poppins", Font.PLAIN, 24));

        textArea.setBackground(new Color(71, 86, 140));
        textArea.setForeground(new Color(255, 255, 255));
        textArea.setCaretColor(new Color(240, 56, 0));

//        SCROLL PANE
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(880, 525));
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

//        FONT SIZE LABEL
        fontLabel = new JLabel("Font");

//        FONT SIZE SPINNER
        fontSizeSpinner = new JSpinner();
        fontSizeSpinner.setPreferredSize(new Dimension(50, 25));
        fontSizeSpinner.setValue(24);
        fontSizeSpinner.addChangeListener(
                e -> textArea.setFont(new Font(textArea.getFont().getFamily(),
                        Font.PLAIN,
                        (int) fontSizeSpinner.getValue()))
        );

//        FONT BOX
        String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        fontBox = new JComboBox<>(fontList);
        fontBox.addActionListener(this);
        fontBox.setSelectedItem("Poppins");

//        ------------ MENU BAR ------------
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");

        openItem.addActionListener(this);
        saveItem.addActionListener(this);
        exitItem.addActionListener(this);

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

//        ------------ \MENU BAR ------------

        this.setJMenuBar(menuBar);
        this.add(fontLabel);
        this.add(fontSizeSpinner);
        this.add(fontBox);
        this.add(scrollPane);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fontBox) {
            textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize()));
        }

        if (e.getSource() == openItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));

            FileNameExtensionFilter nameExtensionFilter = new FileNameExtensionFilter("Text Files", "txt");
            fileChooser.setFileFilter(nameExtensionFilter);

            int response = fileChooser.showOpenDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;

                try {
                    fileIn = new Scanner(file);
                    if (file.isFile()) {
                        textArea.setText("");
                        while (fileIn.hasNextLine()) {
                            String line = fileIn.nextLine() + "\n";
                            textArea.append(line);
                        }
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }

        }
        if (e.getSource() == saveItem) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int response = fileChooser.showSaveDialog(null);

            if (response == JFileChooser.APPROVE_OPTION) {
                File file;
                PrintWriter fileOut = null;

                file = new File(fileChooser.getSelectedFile().getAbsolutePath());

                try {
                    fileOut = new PrintWriter(file);
                    fileOut.println(textArea.getText());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                } finally {
                    assert fileOut != null;
                    fileOut.close();
                }

            }
        }
        if (e.getSource() == exitItem) {
            System.exit(0);
        }
    }
}
