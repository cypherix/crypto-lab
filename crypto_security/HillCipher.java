import java.util.Scanner;

public class HillCipher {

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1; 
    }

    public static int[][] getMatrixInverse(int[][] matrix, int mod) {
        int size = matrix.length;
        int[][] adjugateMatrix = new int[size][size];
        int det = getDeterminant(matrix, size);
        int detInverse = modInverse(det, mod);

        if (size == 2) {
            adjugateMatrix[0][0] = matrix[1][1];
            adjugateMatrix[0][1] = -matrix[0][1];
            adjugateMatrix[1][0] = -matrix[1][0];
            adjugateMatrix[1][1] = matrix[0][0];
        } else {
            throw new UnsupportedOperationException("Matrix inversion is only implemented for 2x2 matrices");
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                adjugateMatrix[i][j] = ((adjugateMatrix[i][j] * detInverse) % mod + mod) % mod;
            }
        }

        return adjugateMatrix;
    }

    public static int[] matrixMultiply(int[][] keyMatrix, int[] vector) {
        int[] result = new int[vector.length];
        for (int i = 0; i < keyMatrix.length; i++) {
            result[i] = 0;
            for (int j = 0; j < keyMatrix[0].length; j++) {
                result[i] += keyMatrix[i][j] * vector[j];
            }
            result[i] = result[i] % 26;
        }
        return result;
    }

    public static String encrypt(String plaintext, int[][] keyMatrix) {
        int n = keyMatrix.length;
        plaintext = plaintext.toUpperCase().replaceAll("\\s", "");
        while (plaintext.length() % n != 0) {
            plaintext += 'X'; 
        }

        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += n) {
            int[] messageVector = new int[n];
            for (int j = 0; j < n; j++) {
                messageVector[j] = plaintext.charAt(i + j) - 'A';
            }
            int[] encryptedVector = matrixMultiply(keyMatrix, messageVector);
            for (int j = 0; j < n; j++) {
                ciphertext.append((char) (encryptedVector[j] + 'A'));
            }
        }

        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, int[][] keyMatrix) {
        int[][] inverseKeyMatrix = getMatrixInverse(keyMatrix, 26);
        return encrypt(ciphertext, inverseKeyMatrix);
    }

    public static int getDeterminant(int[][] matrix, int n) {
        int determinant = 0;
        if (n == 2) {
            determinant = matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        } else {
            throw new UnsupportedOperationException("Determinant calculation is only implemented for 2x2 matrices");
        }
        return determinant % 26;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Encryption
        System.out.println("Enter the size of the key matrix (N for NxN):");
        int n = scanner.nextInt();

        int[][] keyMatrix = new int[n][n];
        System.out.println("Enter the " + n + "x" + n + " key matrix (numbers between 0-25):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine(); 

        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine();

        String ciphertext = encrypt(plaintext, keyMatrix);
        System.out.println("Encrypted text: " + ciphertext);

        // Decryption
        System.out.println("Enter the size of the key matrix for decryption (N for NxN):");
        int decryptionN = scanner.nextInt();

        int[][] decryptionMatrix = new int[decryptionN][decryptionN];
        System.out.println("Enter the " + decryptionN + "x" + decryptionN + " key matrix for decryption (numbers between 0-25):");
        for (int i = 0; i < decryptionN; i++) {
            for (int j = 0; j < decryptionN; j++) {
                decryptionMatrix[i][j] = scanner.nextInt();
            }
        }

        scanner.nextLine(); 

        System.out.println("Enter the ciphertext to decrypt:");
        String textToDecrypt = scanner.nextLine();

        String decryptedText = decrypt(textToDecrypt, decryptionMatrix);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
