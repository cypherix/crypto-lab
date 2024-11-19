import java.util.Scanner;

public class playfair {

    public static char matrix[][] = new char[5][5];  // Initialize the matrix with size 5x5

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text:");
        String plain_text = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("I", "J");
        System.out.println("Processed Plain Text: " + plain_text);

        System.out.println("Enter the key:");
        String key = sc.nextLine().toUpperCase().replaceAll("[^A-Z]", "").replace("I", "J");
        System.out.println("Processed Key: " + key);

        create_table(key);
        print_table();
    }

    public static void create_table(String key) {
        boolean[] isvisited = new boolean[26]; // Fix: The array should be size 26, to cover A-Z
        int row = 0, col = 0;
        char[] char_array = key.toCharArray();

        for (char ch : char_array) {
            if (!isvisited[ch - 'A']) {
                matrix[row][col] = ch;
                isvisited[ch - 'A'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            if (ch == 'J') continue; 
            if (!isvisited[ch - 'A']) {
                matrix[row][col] = ch;
                isvisited[ch - 'A'] = true;
                col++;
                if (col == 5) {
                    col = 0;
                    row++;
                }
            }
        }
    }

    public static void print_table() {
        System.out.println("5x5 Key Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    
}
