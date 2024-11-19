import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESUserInput {

    // Method to convert a user-provided key (as a string) into a DES key
    public static SecretKey getKeyFromString(String keyStr) throws Exception {
        byte[] keyBytes = keyStr.getBytes();
        // Ensure the key is exactly 8 bytes long (56 bits for DES + 8 bits for parity)
        byte[] desKey = new byte[8];
        System.arraycopy(keyBytes, 0, desKey, 0, Math.min(keyBytes.length, 8));
        return new SecretKeySpec(desKey, "DES"); 
    }

    // Method to encrypt data using DES
    public static String encrypt(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    // Method to decrypt data using DES
    public static String decrypt(String encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedData = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedData);
        return new String(decryptedData);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // User input for the key (must be at least 8 characters long)
            System.out.print("Enter a key (at least 8 characters): ");
            String keyStr = scanner.nextLine();

            // Ensure the key is at least 8 characters long
            if (keyStr.length() < 8) {
                System.out.println("Key must be at least 8 characters long!");
                return;
            }

            // Convert the user-provided key to a DES key
            SecretKey key = getKeyFromString(keyStr);

            // User input for the plaintext to be encrypted
            System.out.print("Enter the plaintext to encrypt: ");
            String plainText = scanner.nextLine();

            // Encrypt the plaintext
            String encryptedText = encrypt(plainText, key);
            System.out.println("Encrypted Text: " + encryptedText);

            // Decrypt the ciphertext
            String decryptedText = decrypt(encryptedText, key);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
