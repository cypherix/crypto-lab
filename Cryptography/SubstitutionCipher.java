import java.util.Scanner;

public class SubstitutionCipher {

    public static String processText(String text, char[] substitutionArray, boolean isEncrypt) {
        StringBuilder result = new StringBuilder();

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int index = Character.toLowerCase(c) - 'a';
                char mappedChar = (char) (isEncrypt 
                                          ? (base + substitutionArray[index] - 'a') 
                                          : (base + new String(substitutionArray).indexOf(Character.toLowerCase(c))));
                result.append(mappedChar);
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[] substitutionArray = "mnbvcxzlkjhgfdsapoiuytrewq".toCharArray();
        

        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        String encryptedText = processText(plaintext, substitutionArray, true);
        System.out.println("Encrypted text: " + encryptedText);
        System.out.println("Decrypted text: " + processText(encryptedText, substitutionArray, false));

        scanner.close();
    }
}
