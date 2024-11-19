import java.util.Arrays;
import java.util.Scanner;

public class RowTranspositionCipher {

    // Encrypts the plaintext using row transposition cipher
    public static String encrypt(String plaintext, int[] key) {
        int keyLength = key.length;
        int textLength = plaintext.length();

        // Calculate the number of rows needed
        int numRows = (int) Math.ceil((double) textLength / keyLength);
        char[][] table = new char[numRows][keyLength];

        // Fill the table row by row with plaintext characters
        int index = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (index < textLength) {
                    table[i][j] = plaintext.charAt(index++);
                } else {
                    table[i][j] = 'X'; // Fill empty spaces with a placeholder
                }
            }
        }

        // Read the table column by column based on the key
        StringBuilder ciphertext = new StringBuilder();
        for (int k : key) {
            int columnIndex = k - 1; // Convert key to 0-based index
            for (int i = 0; i < numRows; i++) {
                ciphertext.append(table[i][columnIndex]);
            }
        }

        return ciphertext.toString();
    }

    // Decrypts the ciphertext using row transposition cipher
    public static String decrypt(String ciphertext, int[] key) {
        int keyLength = key.length;
        int textLength = ciphertext.length();

        // Calculate the number of rows
        int numRows = (int) Math.ceil((double) textLength / keyLength);
        char[][] table = new char[numRows][keyLength];

        // Fill the table column by column based on the key
        int index = 0;
        for (int k : key) {
            int columnIndex = k - 1; // Convert key to 0-based index
            for (int i = 0; i < numRows; i++) {
                table[i][columnIndex] = ciphertext.charAt(index++);
            }
        }

        // Read the table row by row to reconstruct the plaintext
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < keyLength; j++) {
                plaintext.append(table[i][j]);
            }
        }

        // Remove trailing placeholder characters (X)
        while (plaintext.charAt(plaintext.length() - 1) == 'X') {
            plaintext.deleteCharAt(plaintext.length() - 1);
        }

        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext
        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine().replaceAll("\\s", "").toUpperCase();

        // Input key
        System.out.println("Enter the key as space-separated numbers (e.g., 3 1 4 2):");
        String[] keyInput = scanner.nextLine().split("\\s+");
        int[] key = Arrays.stream(keyInput).mapToInt(Integer::parseInt).toArray();

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
