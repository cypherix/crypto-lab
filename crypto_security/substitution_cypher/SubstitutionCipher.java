import java.util.Scanner;

public class SubstitutionCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] originalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+-=[]{}|;:',.<>?/~`".toCharArray();
        char[] substitutionChars = "QWERTYUIOPASDFGHJKLZXCVBNMpqrstuvwxyzabcdefghijklmno0987654321!@#$%^&*()_+-=[]{}|;:',.<>?/~`".toCharArray();

        char[] encryptArray = new char[256];
        char[] decryptArray = new char[256];

        for (int i = 0; i < originalChars.length; i++) {
            encryptArray[originalChars[i]] = substitutionChars[i];
            decryptArray[substitutionChars[i]] = originalChars[i];
        }

    
        System.out.print("Enter the text to encrypt: ");
        String text = sc.nextLine();
        String encryptedText = substitute(text, encryptArray);
        System.out.println("Encrypted text: " + encryptedText);

        System.out.print("Enter the text to decrypt: ");
        String encryptedInput = sc.nextLine();
        String decryptedText = substitute(encryptedInput, decryptArray);
        System.out.println("Decrypted text: " + decryptedText);

        sc.close();
    }

    private static String substitute(String text, char[] substitutionArray) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (ch < substitutionArray.length) {
                result.append(substitutionArray[ch]);
            } else {
                result.append(ch); 
            }
        }
        return result.toString();
    }
}
