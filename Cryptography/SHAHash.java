import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAHash {
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String original = "HelloWorld";
        String hash = sha256(original);
        System.out.println("SHA-256 hash of \"" + original + "\": " + hash);
    }
}
