import java.util.Scanner;

public class ExtendedEuclidean {

    public static int[] extendedGCD(int a, int b) {
        if (a == 0) {
            return new int[] { b, 0, 1 }; 
        }
        
        int[] results = extendedGCD(b % a, a);
        int gcd = results[0];
        int x1 = results[1];
        int y1 = results[2];
        
        int x = y1 - (b / a) * x1;
        int y = x1;
        
        return new int[] { gcd, x, y };
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the first integer (a): ");
        int a = scanner.nextInt();
        
        System.out.print("Enter the second integer (b): ");
        int b = scanner.nextInt();
        
        int[] result = extendedGCD(a, b);
        
        System.out.println("GCD: " + result[0]);
        System.out.println("x: " + result[1]);
        System.out.println("y: " + result[2]);
        
        scanner.close();
    }
}
