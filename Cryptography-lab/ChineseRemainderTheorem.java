import java.util.Scanner;

public class ChineseRemainderTheorem {
    public static long modularInverse(long a, long m) {
        long m0 = m, t, q;
        long x0 = 0, x1 = 1;
        if (m == 1) return 0;
        while (a > 1) {
            q = a / m;
            t = m;


            m = a % m;
            a = t;

            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) x1 += m0;

        return x1;
    }

    // Function to find the solution to the system of congruences using CRT
    public static long chineseRemainderTheorem(long[] num, long[] rem) {
        long prod = 1;
        for (long n : num) {
            prod *= n; // Calculate product of all moduli
        }

        long result = 0;
        for (int i = 0; i < num.length; i++) {
            long pp = prod / num[i]; // Partial product
            result += rem[i] * modularInverse(pp, num[i]) * pp;
        }

        return result % prod; // Return the result modulo the product
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the moduli and remainders for 3 equations
        System.out.println("Enter the moduli for the three equations:");
        long[] num = new long[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Modulus " + (i + 1) + ": ");
            num[i] = scanner.nextLong();
        }

        System.out.println("Enter the remainders for the three equations:");
        long[] rem = new long[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Remainder " + (i + 1) + ": ");
            rem[i] = scanner.nextLong();
        }

        // Solve the congruences using CRT
        long result = chineseRemainderTheorem(num, rem);
        long prod = 1;
        for (long n : num) prod *= n; // Calculate product of all moduli for the range of solutions

        System.out.println("\nThe solution is: x ≡ " + result + " (mod " + prod + ")");

        scanner.close();
    }
}
