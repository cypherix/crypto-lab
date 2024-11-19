import java.util.Scanner;

public class EuclideanAlgorithm {

 
    public static int gcd(int a, int b) {
        int r1 = a;
        int r2 = b;
        
        while (r2 > 0) {
            int q = r1 / r2;        
            int r = r1 - q * r2;    
            r1 = r2;               
            r2 = r;                 
        }

        return r1; 
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int a = sc.nextInt();

        System.out.println("Enter the second number: ");
        int b = sc.nextInt();

    
        int result = gcd(a, b);
        System.out.println("The GCD of " + a + " and " + b + " is: " + result);

        sc.close();
    }
}
