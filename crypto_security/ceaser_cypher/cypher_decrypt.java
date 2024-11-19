import java.util.Scanner;

public class cypher_decrypt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the encrypted text: ");
        String enc_text= sc.nextLine();
        enc_text=enc_text.toUpperCase(); 

        System.out.print("Enter the key: ");
        int key = sc.nextInt();

        System.out.println("Encrypted text: " + enc_text);

        String decryptedText = decrypt(enc_text, key);
        System.out.println("Decrypted text: " + decryptedText);

        sc.close();
    }

    public static String decrypt(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (Character.isUpperCase(ch)) {
                char base = 'A';
                ch = (char) ((ch - base - key + 26) % 26 + base);
            }

            result.append(ch);
        }

        return result.toString();
    }
}
