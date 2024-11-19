import java.util.Scanner;

public class cypher_encrypt {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the plain text: ");
        String text= sc.nextLine();
        text=text.toUpperCase();  
        System.out.println("The plain text is: " + text);
        System.out.print("Enter the key value: ");
        int key = sc.nextInt(); 
        String encryptedText = encrypt(text, key);
        System.out.println("The encrypted text is: " + encryptedText);
        sc.close();
    }

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            
            if (Character.isUpperCase(ch)) {  
                char base = 'A';
                ch = (char) ((ch - base + key) % 26 + base);
            }

            result.append(ch);
        }

        return result.toString();
    }
}
