import java.util.Scanner;

public class RowTranspositionCipher {

    public static String encrypt(String plaintext, int[] key) {
        int numRows = (int) Math.ceil((double) plaintext.length() / key.length);
        char[][] grid = new char[numRows][key.length];

        int k = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length; j++) {
                if (k < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(k++);
                } else {
                    grid[i][j] = 'X'; 
                }
            }
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int col : key) {
            for (int row = 0; row < numRows; row++) {
                ciphertext.append(grid[row][col - 1]);
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int[] key) {
        int numRows = (int) Math.ceil((double) ciphertext.length() / key.length);
        char[][] grid = new char[numRows][key.length];

        int k = 0;
        for (int col : key) {
            for (int row = 0; row < numRows; row++) {
                grid[row][col - 1] = ciphertext.charAt(k++);
            }
        }

        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < key.length; j++) {
                plaintext.append(grid[i][j]);
            }
        }

        return plaintext.toString().replaceAll("X+$", ""); 
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine().replaceAll("\\s+", "").toUpperCase();

        System.out.println("Enter the encryption key order (comma-separated):");
        String[] keyStrings = scanner.nextLine().split(",");
        int[] encryptionKey = new int[keyStrings.length];
        for (int i = 0; i < keyStrings.length; i++) {
            encryptionKey[i] = Integer.parseInt(keyStrings[i]);
        }

        String ciphertext = encrypt(plaintext, encryptionKey);
        System.out.println("Encrypted text: " + ciphertext);

        System.out.println("Enter the text to decrypt:");
        String textToDecrypt = scanner.nextLine().replaceAll("\\s+", "").toUpperCase();

        System.out.println("Enter the decryption key order (comma-separated):");
        String[] decryptionKeyStrings = scanner.nextLine().split(",");
        int[] decryptionKey = new int[decryptionKeyStrings.length];
        for (int i = 0; i < decryptionKeyStrings.length; i++) {
            decryptionKey[i] = Integer.parseInt(decryptionKeyStrings[i]);
        }

        String decryptedText = decrypt(textToDecrypt, decryptionKey);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
