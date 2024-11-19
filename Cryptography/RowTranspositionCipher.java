import java.util.Scanner;
import java.util.Arrays;

public class RowTranspositionCipher {

    // Method to encrypt the plaintext using Row Transposition Cipher
    public static String encrypt(String plaintext, int[] key) {
        int keyLength = key.length;
        int textLength = plaintext.length();

        // Padding the plaintext with 'X' to fill the grid if necessary
        int rows = (int) Math.ceil((double) textLength / keyLength);
        char[][] grid = new char[rows][keyLength];
        for (int i = 0; i < rows * keyLength; i++) {
            if (i < textLength) {
                grid[i / keyLength][i % keyLength] = plaintext.charAt(i);
            } else {
                grid[i / keyLength][i % keyLength] = 'X';  // Padding character
            }
        }

        // Reading columns based on the key to form the ciphertext
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < keyLength; i++) {
            int col = key[i] - 1; // Key is 1-indexed, convert to 0-indexed
            for (int j = 0; j < rows; j++) {
                ciphertext.append(grid[j][col]);
            }
        }

        return ciphertext.toString();
    }

    // Method to decrypt the ciphertext using Row Transposition Cipher
    public static String decrypt(String ciphertext, int[] key) {
        int keyLength = key.length;
        int textLength = ciphertext.length();

        int rows = (int) Math.ceil((double) textLength / keyLength);
        char[][] grid = new char[rows][keyLength];

        // Fill the grid column-wise based on the key
        int index = 0;
        for (int i = 0; i < keyLength; i++) {
            int col = key[i] - 1;  // Key is 1-indexed, convert to 0-indexed
            for (int j = 0; j < rows; j++) {
                if (index < textLength) {
                    grid[j][col] = ciphertext.charAt(index++);
                }
            }
        }

        // Reading the grid row-wise to form the plaintext
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyLength; j++) {
                plaintext.append(grid[i][j]);
            }
        }

        // Remove any padding characters (e.g., 'X') added during encryption
        return plaintext.toString().replaceAll("X+$", "");  // Removes trailing padding
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example key (The key determines the order of columns)
        System.out.println("Enter the number of columns (key length): ");
        int keyLength = sc.nextInt();
        int[] key = new int[keyLength];

        System.out.println("Enter the key (e.g., 3 1 4 2): ");
        for (int i = 0; i < keyLength; i++) {
            key[i] = sc.nextInt();
        }

        // Encryption
        System.out.println("Enter the plaintext: ");
        sc.nextLine(); // Consume newline
        String plaintext = sc.nextLine().replaceAll("[^A-Za-z]", "").toUpperCase(); // Clean input
        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted text: " + encryptedText);

        // Decryption
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted text: " + decryptedText);

        sc.close();
    }
}
