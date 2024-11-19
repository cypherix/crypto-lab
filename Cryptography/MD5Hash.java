import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {
    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
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
        String hash = md5(original);
        System.out.println("MD5 hash of \"" + original + "\": " + hash);
    }
}
