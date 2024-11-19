import java.util.Scanner;

public class PlayFairCipher {

    private static char[][] matrix = new char[5][5];

    // Method to generate the key matrix
    public static void generateKeyMatrix(String key) {
        boolean[] alphabet = new boolean[26];
        int x = 0, y = 0;

        for (char c : key.toCharArray()) {
            if (c == 'J')
                c = 'I'; // Treat 'J' as 'I'
            if (!alphabet[c - 'A']) {
                matrix[x][y] = c;
                alphabet[c - 'A'] = true;
                y++;
                if (y == 5) {
                    y = 0;
                    x++;
                }
            }
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J')
                continue;
            if (!alphabet[c - 'A']) {
                matrix[x][y] = c;
                alphabet[c - 'A'] = true;
                y++;
                if (y == 5) {
                    y = 0;
                    x++;
                }
            }
        }
    }

    // Method to preprocess the plaintext for the Playfair Cipher
    public static String preprocessText(String text) {
        text = text.toUpperCase().replaceAll("[^A-Z]", "").replace('J', 'I');
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            result.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) {
                result.append('X');
            }
        }
        if (result.length() % 2 != 0) {
            result.append('X');
        }
        return result.toString();
    }

    // Method to find the position of a character in the key matrix
    public static int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[] { i, j };
                }
            }
        }
        return null;
    }

    // Method to encrypt the plaintext using Playfair Cipher
    public static String encrypt(String plaintext) {
        StringBuilder encryptedText = new StringBuilder();
        plaintext = preprocessText(plaintext);
        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);
            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) { // Same row
                encryptedText.append(matrix[posA[0]][(posA[1] + 1) % 5]);
                encryptedText.append(matrix[posB[0]][(posB[1] + 1) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                encryptedText.append(matrix[(posA[0] + 1) % 5][posA[1]]);
                encryptedText.append(matrix[(posB[0] + 1) % 5][posB[1]]);
            } else { // Rectangle
                encryptedText.append(matrix[posA[0]][posB[1]]);
                encryptedText.append(matrix[posB[0]][posA[1]]);
            }
        }
        return encryptedText.toString();
    }

    // Method to decrypt the ciphertext using Playfair Cipher
    public static String decrypt(String ciphertext) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);
            int[] posA = findPosition(a);
            int[] posB = findPosition(b);

            if (posA[0] == posB[0]) { // Same row
                decryptedText.append(matrix[posA[0]][(posA[1] + 4) % 5]);
                decryptedText.append(matrix[posB[0]][(posB[1] + 4) % 5]);
            } else if (posA[1] == posB[1]) { // Same column
                decryptedText.append(matrix[(posA[0] + 4) % 5][posA[1]]);
                decryptedText.append(matrix[(posB[0] + 4) % 5][posB[1]]);
            } else { // Rectangle
                decryptedText.append(matrix[posA[0]][posB[1]]);
                decryptedText.append(matrix[posB[0]][posA[1]]);
            }
        }
        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the key for Playfair Cipher
        System.out.println("Enter the key:");
        String key = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");
        generateKeyMatrix(key);

        // Input the plaintext
        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine();

        // Encrypt the plaintext
        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the ciphertext
        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
