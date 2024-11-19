import java.util.Scanner;

public class VigenereCipher {

    // Encrypts the plaintext using the Vigenère cipher
    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        plaintext = plaintext.toUpperCase();
        key = generateKey(plaintext, key).toUpperCase();

        for (int i = 0; i < plaintext.length(); i++) {
            char plainChar = plaintext.charAt(i);

            if (plainChar >= 'A' && plainChar <= 'Z') {
                int encryptedChar = ((plainChar - 'A') + (key.charAt(i) - 'A')) % 26;
                ciphertext.append((char) (encryptedChar + 'A'));
            } else {
                ciphertext.append(plainChar); // Keep non-alphabetic characters unchanged
            }
        }

        return ciphertext.toString();
    }

    // Decrypts the ciphertext using the Vigenère cipher
    public static String decrypt(String ciphertext, String key) {
        StringBuilder plaintext = new StringBuilder();
        ciphertext = ciphertext.toUpperCase();
        key = generateKey(ciphertext, key).toUpperCase();

        for (int i = 0; i < ciphertext.length(); i++) {
            char cipherChar = ciphertext.charAt(i);

            if (cipherChar >= 'A' && cipherChar <= 'Z') {
                int decryptedChar = ((cipherChar - 'A') - (key.charAt(i) - 'A') + 26) % 26;
                plaintext.append((char) (decryptedChar + 'A'));
            } else {
                plaintext.append(cipherChar); // Keep non-alphabetic characters unchanged
            }
        }

        return plaintext.toString();
    }

    // Generates a repeated key matching the length of the text
    private static String generateKey(String text, String key) {
        StringBuilder extendedKey = new StringBuilder();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            if (currentChar >= 'A' && currentChar <= 'Z') {
                extendedKey.append(key.charAt(j));
                j = (j + 1) % key.length();
            } else {
                extendedKey.append(currentChar); // Non-alphabetic characters are skipped
            }
        }
        return extendedKey.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input plaintext
        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine();

        // Input key
        System.out.println("Enter the encryption key:");
        String key = scanner.nextLine();

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + ciphertext);

        // Decrypt the ciphertext
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
