import java.util.Scanner;

public class VigenereCipher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the plaintext:");
        String plaintext = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        System.out.println("Enter the keyword for encryption:");
        String keywordForEncryption = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        String encryptedText = encrypt(plaintext, keywordForEncryption);
        System.out.println("Encrypted Text: " + encryptedText);

        System.out.println("Enter the encrypted text to decrypt:");
        String encryptedInput = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        System.out.println("Enter the keyword for decryption:");
        String keywordForDecryption = scanner.nextLine().toUpperCase().replaceAll("[^A-Z]", "");

        String decryptedText = decrypt(encryptedInput, keywordForDecryption);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }

    private static String encrypt(String plaintext, String keyword) {
        StringBuilder encryptedText = new StringBuilder();
        keyword = extendKeyword(plaintext, keyword);

        for (int i = 0; i < plaintext.length(); i++) {
            char p = plaintext.charAt(i);
            char k = keyword.charAt(i);
            char e = (char) (((p - 'A') + (k - 'A')) % 26 + 'A');
            encryptedText.append(e);
        }

        return encryptedText.toString();
    }

    private static String decrypt(String ciphertext, String keyword) {
        StringBuilder decryptedText = new StringBuilder();
        keyword = extendKeyword(ciphertext, keyword);

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            char k = keyword.charAt(i);
            char d = (char) (((c - 'A') - (k - 'A') + 26) % 26 + 'A');
            decryptedText.append(d);
        }

        return decryptedText.toString();
    }

    private static String extendKeyword(String text, String keyword) {
        StringBuilder extendedKeyword = new StringBuilder();
        int keywordLength = keyword.length();

        for (int i = 0; i < text.length(); i++) {
            extendedKeyword.append(keyword.charAt(i % keywordLength));
        }

        return extendedKeyword.toString();
    }
}
