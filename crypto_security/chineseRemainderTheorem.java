import java.util.Scanner;

public class ChineseRemainderTheorem {

    // Function to calculate gcd using Euclidean algorithm
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Extended Euclidean Algorithm to find the modular inverse
    // Returns x such that (a * x) % b == 1
    public static int modInverse(int a, int b) {
        int m0 = b, t, q;
        int x0 = 0, x1 = 1;

        if (b == 1)
            return 0;

        // Apply extended Euclidean algorithm
        while (a > 1) {
            // q is quotient
            q = a / b;

            t = b;

            // b is remainder now, process same as Euclid's algorithm
            b = a % b;
            a = t;

            t = x0;

            x0 = x1 - q * x0;

            x1 = t;
        }

        // Make x1 positive
        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    // Function to apply the Chinese Remainder Theorem
    // n[] represents the moduli array and a[] represents the remainder array
    public static int findMinX(int n[], int a[], int k) {
        // Compute product of all numbers
        int prod = 1;
        for (int i = 0; i < k; i++)
            prod *= n[i];

        // Initialize result
        int result = 0;

        // Apply the formula: x = sum(a[i] * N_i * M_i)
        for (int i = 0; i < k; i++) {
            // Compute N_i = prod / n[i]
            int Ni = prod / n[i];

            // Compute M_i = modInverse(N_i, n[i])
            int Mi = modInverse(Ni, n[i]);

            // Add a_i * N_i * M_i to the result
            result += a[i] * Ni * Mi;
        }

        // Return result mod prod
        return result % prod;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: Number of equations
        System.out.print("Enter the number of equations: ");
        int k = scanner.nextInt();

        // Arrays for moduli and remainders
        int[] n = new int[k];
        int[] a = new int[k];

        // Input: Moduli and remainders
        for (int i = 0; i < k; i++) {
            System.out.print("Enter modulus n[" + (i + 1) + "]: ");
            n[i] = scanner.nextInt();
            System.out.print("Enter remainder a[" + (i + 1) + "]: ");
            a[i] = scanner.nextInt();
        }

        // Calculate solution using CRT
        int result = findMinX(n, a, k);
        System.out.println("The smallest x that satisfies the given system of congruences is: " + result);

        scanner.close();
    }
}
