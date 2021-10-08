import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class EncryptionProgram {
    private final Scanner scanner;
    private final ArrayList<Character> list;
    private ArrayList<Character> key;
    private char[] message;

    // constructor to initialize the members
    EncryptionProgram() {
        scanner = new Scanner(System.in);
        list = new ArrayList<>();
        key = new ArrayList<>();

        newMessage();
        takeInput();
    }

    // method to ask task to do from user
    private void takeInput() {
        while (true) {
            System.out.println("(H)elp");
            System.out.print("-> ");
            char response = scanner.nextLine().toUpperCase().charAt(0);

            switch (response) {
                case 'H' -> showHelp();
                case 'S' -> getMessage();
                case 'E' -> encrypt();
                case 'D' -> decrypt();
                case 'Q' -> System.exit(0);
            }
        }
    }

    private void showHelp() {
        System.out.println("|`````````````````````````````|");
        System.out.println("| S -> to show the message    |");
        System.out.println("| E -> to encrypt the message |");
        System.out.println("| D -> to decrypt the message |");
        System.out.println("| Q -> to quit the session    |");
        System.out.println("|.............................|");
    }

    // method to get message from user
    private void newMessage() {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("ENTER YOUR MESSAGE -> ");
        message = scanner.nextLine().toCharArray();
        System.out.println("--------------------------------------------------------------------------------");

        generateKey();
    }

    // method to show encrypted\decrypted message
    private void getMessage() {
        System.out.print("YOUR MESSAGE IS: ");
        for (char c : message) {
            System.out.print(c);
        }
        System.out.println();
    }

    // method to generate new key
    private void generateKey() {
        char character = ' ';
        // resetting values for the new key
        key.clear();

        // list of characters
        for (int i = 32; i <= 126; i++) {
            list.add(character);
            character++;
        }


        // generating new key
        key = new ArrayList<>(list);
        Collections.shuffle(key);

        System.out.println("KEY GENERATED");
    }

    // method to encrypt the plain text
    private void encrypt() {
        for (int i = 0; i < message.length; i++) {
            for (int j = 0; j < list.size(); j++) {
                if (message[i] == list.get(j)) {
                    message[i] = key.get(j);
                    break;
                }
            }
        }

        System.out.println("MESSAGE ENCRYPTED");
        for (char x : message) {
            System.out.print(x);
        }
        System.out.println();
    }

    // method to decrypt the cipher text
    private void decrypt() {
        for (int i = 0; i < message.length; i++) {
            for (int j = 0; j < key.size(); j++) {
                if (message[i] == key.get(j)) {
                    message[i] = list.get(j);
                    break;
                }
            }
        }

        System.out.println("MESSAGE DECRYPTED");
        for (char x : message) {
            System.out.print(x);
        }
        System.out.println();
    }
}
