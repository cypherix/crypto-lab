import java.util.Scanner;

public class HillCipher {

    // Hill Cipher Encryption (2x2 matrix for simplicity)
    public static String hillEncrypt(String plaintext, int[][] keyMatrix) {
        plaintext = plaintext.toUpperCase();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += 2) {
            char x = plaintext.charAt(i);
            char y = (i + 1 < plaintext.length()) ? plaintext.charAt(i + 1) : 'X';

            int x1 = x - 'A';
            int y1 = y - 'A';

            int c1 = (keyMatrix[0][0] * x1 + keyMatrix[0][1] * y1) % 26;
            int c2 = (keyMatrix[1][0] * x1 + keyMatrix[1][1] * y1) % 26;

            result.append((char) (c1 + 'A'));
            result.append((char) (c2 + 'A'));
        }

        return result.toString();
    }

    // Hill Cipher Decryption (2x2 matrix for simplicity)
    public static String hillDecrypt(String ciphertext, int[][] inverseMatrix) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2) {
            char x = ciphertext.charAt(i);
            char y = ciphertext.charAt(i + 1);

            int x1 = x - 'A';
            int y1 = y - 'A';

            int p1 = (inverseMatrix[0][0] * x1 + inverseMatrix[0][1] * y1) % 26;
            int p2 = (inverseMatrix[1][0] * x1 + inverseMatrix[1][1] * y1) % 26;

            p1 = (p1 + 26) % 26; 
            p2 = (p2 + 26) % 26;

            result.append((char) (p1 + 'A'));
            result.append((char) (p2 + 'A'));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine();

        System.out.println("\nHill Cipher:");
        int[][] hillKeyMatrix = { { 2, 1 }, { 3, 4 } }; // Example key matrix
        int[][] hillInverseMatrix = { { 10, 0 }, { 0, 10 } }; // Example inverse matrix for decryption

        String hillEncrypted = hillEncrypt(plaintext, hillKeyMatrix);
        System.out.println("Encrypted Text: " + hillEncrypted);

        String hillDecrypted = hillDecrypt(hillEncrypted, hillInverseMatrix);
        System.out.println("Decrypted Text: " + hillDecrypted);

        scanner.close();
    }
}
