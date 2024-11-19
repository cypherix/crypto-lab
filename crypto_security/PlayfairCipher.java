import java.util.Scanner;

public class PlayfairCipher {

    private static char[][] keyMatrix = new char[5][5];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the plain text from the user
        System.out.println("Enter the text to encrypt:");
        String inputText = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        // Get the keyword to generate the key matrix for encryption
        System.out.println("Enter the keyword for encryption:");
        String keywordForEncryption = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        // Generate the key matrix for encryption
        generateKeyMatrix(keywordForEncryption);

        // Prepare and encrypt the input text
        String preparedText = prepareText(inputText);
        String encryptedText = encrypt(preparedText);
        System.out.println("Encrypted Text: " + encryptedText);

        // Get the encrypted text and the keyword for decryption
        System.out.println("Enter the encrypted text to decrypt:");
        String encryptedInput = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        // Get the keyword to generate the key matrix for decryption
        System.out.println("Enter the keyword for decryption:");
        String keywordForDecryption = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("J", "I");

        // Generate the key matrix for decryption
        generateKeyMatrix(keywordForDecryption);

        // Decrypt the encrypted text
        String decryptedText = decrypt(encryptedInput);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }

    // Method to generate the key matrix
    private static void generateKeyMatrix(String keyword) {
        boolean[] alphabetUsed = new boolean[26];
        int row = 0, col = 0;

        // Fill the matrix with the keyword
        for (char c : keyword.toCharArray()) {
            if (!alphabetUsed[c - 'A']) {
                keyMatrix[row][col] = c;
                alphabetUsed[c - 'A'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        // Fill the remaining matrix with the rest of the alphabet
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue; // Skip 'J'
            if (!alphabetUsed[c - 'A']) {
                keyMatrix[row][col] = c;
                alphabetUsed[c - 'A'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    // Method to prepare the text for encryption
    private static String prepareText(String text) {
        StringBuilder preparedText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char firstChar = text.charAt(i);
            char secondChar = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (firstChar == secondChar) {
                preparedText.append(firstChar).append('X');
            } else {
                preparedText.append(firstChar).append(secondChar);
                i++;
            }
        }

        if (preparedText.length() % 2 != 0) {
            preparedText.append('X');
        }

        return preparedText.toString();
    }

    // Method to encrypt the prepared text using the key matrix
    private static String encrypt(String text) {
        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char firstChar = text.charAt(i);
            char secondChar = text.charAt(i + 1);

            int[] firstPos = getCharPosition(firstChar);
            int[] secondPos = getCharPosition(secondChar);

            if (firstPos[0] == secondPos[0]) {
                // Same row
                encryptedText.append(keyMatrix[firstPos[0]][(firstPos[1] + 1) % 5]);
                encryptedText.append(keyMatrix[secondPos[0]][(secondPos[1] + 1) % 5]);
            } else if (firstPos[1] == secondPos[1]) {
                // Same column
                encryptedText.append(keyMatrix[(firstPos[0] + 1) % 5][firstPos[1]]);
                encryptedText.append(keyMatrix[(secondPos[0] + 1) % 5][secondPos[1]]);
            } else {
                // Rectangle swap
                encryptedText.append(keyMatrix[firstPos[0]][secondPos[1]]);
                encryptedText.append(keyMatrix[secondPos[0]][firstPos[1]]);
            }
        }

        return encryptedText.toString();
    }

    // Method to decrypt the encrypted text using the key matrix
    private static String decrypt(String text) {
        StringBuilder decryptedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char firstChar = text.charAt(i);
            char secondChar = text.charAt(i + 1);

            int[] firstPos = getCharPosition(firstChar);
            int[] secondPos = getCharPosition(secondChar);

            if (firstPos[0] == secondPos[0]) {
                // Same row
                decryptedText.append(keyMatrix[firstPos[0]][(firstPos[1] + 4) % 5]);
                decryptedText.append(keyMatrix[secondPos[0]][(secondPos[1] + 4) % 5]);
            } else if (firstPos[1] == secondPos[1]) {
                // Same column
                decryptedText.append(keyMatrix[(firstPos[0] + 4) % 5][firstPos[1]]);
                decryptedText.append(keyMatrix[(secondPos[0] + 4) % 5][secondPos[1]]);
            } else {
                // Rectangle swap
                decryptedText.append(keyMatrix[firstPos[0]][secondPos[1]]);
                decryptedText.append(keyMatrix[secondPos[0]][firstPos[1]]);
            }
        }

        return decryptedText.toString();
    }

    // Method to find the position of a character in the key matrix
    private static int[] getCharPosition(char c) {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                if (keyMatrix[row][col] == c) {
                    return new int[]{row, col};
                }
            }
        }
        return null; // Should not reach here
    }
}
