import java.util.Scanner;

public class ExtendedEuclideanAlgorithm {

    // Method to compute GCD using the Extended Euclidean Algorithm
    public static int extendedGcd(int a, int b, int[] coeffs) {
        int r1 = a, r2 = b;
        int s1 = 1, s2 = 0;
        int t1 = 0, t2 = 1;

        while (r2 != 0) {
            int q = r1 / r2;         // Compute quotient
            int r = r1 - q * r2;     // Compute remainder
            r1 = r2;
            r2 = r;

            // Update s values
            int s = s1 - q * s2;
            s1 = s2;
            s2 = s;

            // Update t values
            int t = t1 - q * t2;
            t1 = t2;
            t2 = t;
        }

        // Store the Bézout coefficients
        coeffs[0] = s1;  // s (coefficient for a)
        coeffs[1] = t1;  // t (coefficient for b)
        
        return r1; // Return GCD
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the first number: ");
        int a = sc.nextInt();

        System.out.println("Enter the second number: ");
        int b = sc.nextInt();

        // Array to store Bézout coefficients s and t
        int[] coeffs = new int[2];

        // Calculate GCD and Bézout coefficients
        int gcd = extendedGcd(a, b, coeffs);
        int s = coeffs[0];  // Bézout coefficient for a
        int t = coeffs[1];  // Bézout coefficient for b

        System.out.println("The GCD of " + a + " and " + b + " is: " + gcd);
        System.out.println("Bézout coefficients: s = " + s + ", t = " + t);
        System.out.println("Equation: " + s + " * " + a + " + " + t + " * " + b + " = " + gcd);

        sc.close();
    }
}
