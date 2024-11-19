import java.util.Scanner;

public class CaeserCipher{
    public static String encrypt(String plaintext, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int asciiOffset = Character.isUpperCase(c) ? 'A' : 'a';
                int shifted = (c - asciiOffset + shift) % 26;
                if (shifted < 0) {
                    shifted += 26;  
                }
                encryptedText.append((char) (shifted + asciiOffset));
            } else {
                encryptedText.append(c);
            }
        }
        return encryptedText.toString();
    }

        public static String decrypt(String ciphertext, int shift){
            return encrypt(ciphertext, -shift);
        }

    public class CaeserCipherBruteForce {
        public static void bruteForceDecrypt(String ciphertext)
        {
            for(int shift = 1; shift < 26; shift++){
                String decryptText = CaeserCipher.decrypt(ciphertext, shift);
                System.out.println("Shift: " + shift + ": " + decryptText);

            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter shift: ");
        int shift = scanner.nextInt();  
        scanner.nextLine();

        String encryptedText = CaeserCipher.encrypt(plaintext, shift);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = CaeserCipher.decrypt(encryptedText, shift);
        System.out.println("Decrypted text: " + decryptedText);

        CaeserCipherBruteForce.bruteForceDecrypt(encryptedText);
        
        for(int shifts = 1; shifts < 26; shifts++){
            String decryptText = CaeserCipher.decrypt(encryptedText, shifts);
            if(decryptText.equals(plaintext))
            System.out.println("Key is: " + shifts + ": " + decryptText);
        }


        scanner.close();
    }
}
