
public class PlayFairCipher {
    private static String format(String text) {
        text = text.replaceAll("[^A-Za-z]", "").toUpperCase().replace("J", "I");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            sb.append(text.charAt(i));
            if (i + 1 < text.length() && text.charAt(i) == text.charAt(i + 1)) sb.append('X');
        }
        if (sb.length() % 2 != 0) sb.append('X');
        return sb.toString();
    }

    private static char[][] generateMatrix(String key) {
        boolean[] used = new boolean[26];
        char[][] matrix = new char[5][5];
        key = (key + "ABCDEFGHIKLMNOPQRSTUVWXYZ").toUpperCase().replace("J", "");
        int idx = 0;
        for (char c : key.toCharArray()) {
            if (!used[c - 'A']) {
                matrix[idx / 5][idx % 5] = c;
                used[c - 'A'] = true;
                idx++;
            }
        }
        return matrix;
    }

    private static String cipher(String text, char[][] matrix, boolean encode) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i), b = text.charAt(i + 1);
            int[] posA = findPos(matrix, a), posB = findPos(matrix, b);
            if (posA[0] == posB[0]) {
                result.append(matrix[posA[0]][(posA[1] + (encode ? 1 : 4)) % 5])
                      .append(matrix[posB[0]][(posB[1] + (encode ? 1 : 4)) % 5]);
            } else if (posA[1] == posB[1]) {
                result.append(matrix[(posA[0] + (encode ? 1 : 4)) % 5][posA[1]])
                      .append(matrix[(posB[0] + (encode ? 1 : 4)) % 5][posB[1]]);
            } else {
                result.append(matrix[posA[0]][posB[1]]).append(matrix[posB[0]][posA[1]]);
            }
        }
        return result.toString();
    }

    private static int[] findPos(char[][] matrix, char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c) return new int[]{i, j};
        return null;
    }

    public static void main(String[] args) {
        String key = "KEYWORD";
        String plaintext = "HELLO WORLD";
        char[][] matrix = generateMatrix(key);
        String formattedText = format(plaintext);
        String encrypted = cipher(formattedText, matrix, true);
        String decrypted = cipher(encrypted, matrix, false);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}
