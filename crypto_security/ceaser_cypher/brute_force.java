import java.util.Scanner;

public class brute_force {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the encrypted text: ");
        String enc_text= sc.nextLine();
        enc_text=enc_text.toUpperCase(); 
       
        System.out.println("Encrypted text: " + enc_text);

        for (int key = 1; key < 26; key++) {
            StringBuilder result = new StringBuilder();

            for (int i = 0; i < enc_text.length(); i++) {
                char ch = enc_text.charAt(i);

                if (Character.isUpperCase(ch)) {
                    char base = 'A';
                    ch = (char) ((ch - base - key + 26) % 26 + base);
                }

                result.append(ch);
            }

            System.out.println("Key " + key + ": " + result.toString());
        }

        sc.close();
    }
}
