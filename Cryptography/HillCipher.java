import java.util.Scanner;

public class HillCipher {
    
    // Method to find the modulo inverse of a number under mod 26
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return 1;
    }

    // Method to encrypt the plaintext
    public static String encrypt(String plaintext, int[][] keyMatrix) {
        plaintext = plaintext.toUpperCase().replaceAll("[^A-Z]", "");  // Clean input
        if (plaintext.length() % 2 != 0) {  // Padding if plaintext length is odd
            plaintext += "X";
        }

        StringBuilder cipherText = new StringBuilder();
        
        // For every pair of characters in the plaintext
        for (int i = 0; i < plaintext.length(); i += 2) {
            int[] plaintextVector = {
                plaintext.charAt(i) - 'A',
                plaintext.charAt(i + 1) - 'A'
            };

            // Matrix multiplication to get ciphertext vector
            int[] cipherTextVector = new int[2];
            cipherTextVector[0] = (keyMatrix[0][0] * plaintextVector[0] + keyMatrix[0][1] * plaintextVector[1]) % 26;
            cipherTextVector[1] = (keyMatrix[1][0] * plaintextVector[0] + keyMatrix[1][1] * plaintextVector[1]) % 26;

            // Append ciphertext
            cipherText.append((char) (cipherTextVector[0] + 'A'));
            cipherText.append((char) (cipherTextVector[1] + 'A'));
        }

        return cipherText.toString();
    }

    // Method to decrypt the ciphertext
    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        ciphertext = ciphertext.toUpperCase().replaceAll("[^A-Z]", "");  // Clean input
        
        // Calculate determinant of the key matrix
        int determinant = (keyMatrix[0][0] * keyMatrix[1][1] - keyMatrix[0][1] * keyMatrix[1][0]) % 26;
        if (determinant < 0) {
            determinant += 26;  // To handle negative determinant
        }
        
        // Find modular inverse of the determinant
        int inverseDeterminant = modInverse(determinant, 26);

        // Adjoint matrix
        int[][] inverseKeyMatrix = new int[2][2];
        inverseKeyMatrix[0][0] = keyMatrix[1][1] * inverseDeterminant % 26;
        inverseKeyMatrix[0][1] = (-keyMatrix[0][1] + 26) * inverseDeterminant % 26;
        inverseKeyMatrix[1][0] = (-keyMatrix[1][0] + 26) * inverseDeterminant % 26;
        inverseKeyMatrix[1][1] = keyMatrix[0][0] * inverseDeterminant % 26;

        StringBuilder plainText = new StringBuilder();

        // For every pair of characters in the ciphertext
        for (int i = 0; i < ciphertext.length(); i += 2) {
            int[] cipherTextVector = {
                ciphertext.charAt(i) - 'A',
                ciphertext.charAt(i + 1) - 'A'
            };

            // Matrix multiplication to get plaintext vector
            int[] plainTextVector = new int[2];
            plainTextVector[0] = (inverseKeyMatrix[0][0] * cipherTextVector[0] + inverseKeyMatrix[0][1] * cipherTextVector[1]) % 26;
            plainTextVector[1] = (inverseKeyMatrix[1][0] * cipherTextVector[0] + inverseKeyMatrix[1][1] * cipherTextVector[1]) % 26;

            // Append plaintext
            plainText.append((char) (plainTextVector[0] + 'A'));
            plainText.append((char) (plainTextVector[1] + 'A'));
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Example: Using a 2x2 key matrix
        int[][] keyMatrix = { { 3, 3 }, { 2, 5 } };

        System.out.println("Enter the plaintext: ");
        String plaintext = sc.nextLine();
        
        // Encryption
        String encrypted = encrypt(plaintext, keyMatrix);
        System.out.println("Encrypted text: " + encrypted);

        // Decryption
        String decrypted = decrypt(encrypted, keyMatrix);
        System.out.println("Decrypted text: " + decrypted);

        sc.close();
    }
}
