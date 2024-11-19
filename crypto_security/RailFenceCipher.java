import java.util.Scanner;

public class RailFenceCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text to encrypt:");
        String text = scanner.nextLine();

        System.out.println("Enter the number of rails:");
        int keyForEncryption = scanner.nextInt();
        scanner.nextLine();

        String encryptedText = encrypt(text, keyForEncryption);
        System.out.println("Encrypted Text: " + encryptedText);

        System.out.println("Enter the encrypted text to decrypt:");
        String encryptedInput = scanner.nextLine();

        System.out.println("Enter the number of rails for decryption:");
        int keyForDecryption = scanner.nextInt();

        String decryptedText = decrypt(encryptedInput, keyForDecryption);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }

    private static String encrypt(String text, int key) {
        if (key == 1) return text;

        char[][] rail = new char[key][text.length()];

        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                rail[i][j] = '\n';

        boolean directionDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            if (row == 0 || row == key - 1)
                directionDown = !directionDown;

            rail[row][col++] = text.charAt(i);

            row = directionDown ? row + 1 : row - 1;
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++)
            for (int j = 0; j < text.length(); j++)
                if (rail[i][j] != '\n')
                    result.append(rail[i][j]);

        return result.toString();
    }

    private static String decrypt(String cipher, int key) {
        if (key == 1) return cipher;

        char[][] rail = new char[key][cipher.length()];

        for (int i = 0; i < key; i++)
            for (int j = 0; j < cipher.length(); j++)
                rail[i][j] = '\n';

        boolean directionDown = false;
        int row = 0, col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0 || row == key - 1)
                directionDown = !directionDown;

            rail[row][col++] = '*';

            row = directionDown ? row + 1 : row - 1;
        }

        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*' && index < cipher.length()) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        directionDown = false;
        row = 0;
        col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0 || row == key - 1)
                directionDown = !directionDown;

            result.append(rail[row][col++]);

            row = directionDown ? row + 1 : row - 1;
        }

        return result.toString();
    }
}
