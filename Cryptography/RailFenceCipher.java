public class RailFenceCipher {

    // Method to encrypt using Rail Fence Cipher
    public static String encrypt(String text, int key) {
        // Create a matrix to cipher plain text
        char[][] rail = new char[key][text.length()];

        // Fill the rail matrix with '\n'
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        // Start filling the rail matrix
        boolean dir_down = false;
        int row = 0, col = 0;

        for (int i = 0; i < text.length(); i++) {
            // Check the direction of the rail
            if (row == 0 || row == key - 1)
                dir_down = !dir_down;

            // Fill the corresponding rail matrix with the current character
            rail[row][col++] = text.charAt(i);

            // Move to the next row in the current direction
            row = dir_down ? row + 1 : row - 1;
        }

        // Construct the encrypted string
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < text.length(); j++) {
                if (rail[i][j] != '\n') {
                    result.append(rail[i][j]);
                }
            }
        }

        return result.toString();
    }

    // Method to decrypt using Rail Fence Cipher
    public static String decrypt(String cipher, int key) {
        // Create a matrix to decipher the cipher text
        char[][] rail = new char[key][cipher.length()];

        // Fill the rail matrix with '\n'
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                rail[i][j] = '\n';
            }
        }

        // Mark the places where characters are placed
        boolean dir_down = false;
        int row = 0, col = 0;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0 || row == key - 1)
                dir_down = !dir_down;

            rail[row][col++] = '*';

            row = dir_down ? row + 1 : row - 1;
        }

        // Fill the rail matrix with the actual cipher text
        int index = 0;
        for (int i = 0; i < key; i++) {
            for (int j = 0; j < cipher.length(); j++) {
                if (rail[i][j] == '*' && index < cipher.length()) {
                    rail[i][j] = cipher.charAt(index++);
                }
            }
        }

        // Construct the decrypted string
        StringBuilder result = new StringBuilder();

        row = 0;
        col = 0;
        dir_down = false;

        for (int i = 0; i < cipher.length(); i++) {
            if (row == 0 || row == key - 1)
                dir_down = !dir_down;

            if (rail[row][col] != '\n') {
                result.append(rail[row][col++]);
            }

            row = dir_down ? row + 1 : row - 1;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String text = "HELLOWORLD";
        int key = 3;

        // Encrypting
        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        // Decrypting
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted Text: " + decrypted);
    }
}
