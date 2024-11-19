import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DESAlgorithm {


    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56); 
        return keyGen.generateKey();
    }

    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedTextBytes = cipher.doFinal(plainText.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedTextBytes);
    }


    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedTextBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedTextBytes);
    }

    public static void main(String[] args) {
        try {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the plaintext to encrypt: ");
            String plainText = sc.nextLine();

            SecretKey secretKey = generateKey();
            System.out.println("DES Key: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));

            String encryptedText = encrypt(plainText, secretKey);
            System.out.println("Encrypted Text: " + encryptedText);

            String decryptedText = decrypt(encryptedText, secretKey);
            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
