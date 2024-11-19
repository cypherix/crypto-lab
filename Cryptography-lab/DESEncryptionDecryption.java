import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESEncryptionDecryption {

    // Encrypts a string using DES
    public static String encrypt(String plaintext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to readable format
    }

    // Decrypts a string using DES
    public static String decrypt(String ciphertext, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext); // Decode Base64
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);

            // Input secret key (8 bytes for DES)
            System.out.println("Enter an 8-character secret key:");
            String keyInput = scanner.nextLine();
            while (keyInput.length() != 8) {
                System.out.println("Invalid key length! Please enter exactly 8 characters:");
                keyInput = scanner.nextLine();
            }
            SecretKey key = new SecretKeySpec(keyInput.getBytes(), "DES");

            // Input plaintext
            System.out.println("Enter plaintext to encrypt:");
            String plaintext = scanner.nextLine();

            // Encrypt the plaintext
            String encryptedText = encrypt(plaintext, key);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the ciphertext
            String decryptedText = decrypt(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);

            scanner.close();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
