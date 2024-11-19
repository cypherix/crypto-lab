import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    
    public static String sha256(String input) {
        try {
            // Create a MessageDigest instance for SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Perform the hash computation
            byte[] hashBytes = digest.digest(input.getBytes());

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // Handle exception appropriately
        }
    }

    public static void main(String[] args) {
        String input = "Hello, World!";
        String hash = sha256(input);
        System.out.println("SHA-256 Hash of '" + input + "' is: " + hash);
    }
}
