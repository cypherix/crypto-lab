import java.util.Scanner;

public class SubstitutionCipher {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SUBSTITUTION_KEY = "QWERTYUIOPLKJHGFDSAZXCVBNM"; 

    public static String encrypt(String plainText) {
        plainText = plainText.toUpperCase();
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char currentChar = plainText.charAt(i);
            if (Character.isLetter(currentChar)) {
                int index = ALPHABET.indexOf(currentChar);
                cipherText.append(SUBSTITUTION_KEY.charAt(index));
            } else {
                cipherText.append(currentChar); 
            }
        }

        return cipherText.toString();
    }

    public static String decrypt(String cipherText) {
        cipherText = cipherText.toUpperCase();
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);
            if (Character.isLetter(currentChar)) {
                int index = SUBSTITUTION_KEY.indexOf(currentChar);
                plainText.append(ALPHABET.charAt(index));
            } else {
                plainText.append(currentChar); 
            }
        }

        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text to encrypt:");
        String inputText = scanner.nextLine();

        String encryptedText = encrypt(inputText);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }
}
