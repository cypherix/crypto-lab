import java.util.Scanner;

public class EulerTheorem {

    // Function to calculate gcd
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Function to calculate Euler's Totient Function phi(n)
    public static int eulerTotient(int n) {
        int result = 1; // Start with 1 because gcd(n, 1) is always 1

        for (int i = 2; i < n; i++) {
            if (gcd(i, n) == 1) {
                result++;
            }
        }
        return result;
    }

    // Function to perform modular exponentiation
    // (base^exp) % mod
    public static int modExp(int base, int exp, int mod) {
        int result = 1;
        base = base % mod; // Handle large base numbers

        while (exp > 0) {
            // If exp is odd, multiply base with result
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            // Now exp must be even, so we divide it by 2
            exp = exp >> 1;
            base = (base * base) % mod; // Square the base
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number (a): ");
        int a = scanner.nextInt();

        System.out.print("Enter modulus (n): ");
        int n = scanner.nextInt();

        // Calculate gcd to ensure a and n are coprime
        if (gcd(a, n) != 1) {
            System.out.println("a and n are not coprime. Euler's Theorem does not apply.");
            return;
        }

        // Calculate Euler's Totient Function φ(n)
        int phi = eulerTotient(n);
        System.out.println("Euler's Totient φ(" + n + ") = " + phi);

        // Apply Euler's Theorem: a^phi(n) ≡ 1 (mod n)
        int result = modExp(a, phi, n);
        System.out.println(a + "^" + phi + " ≡ " + result + " (mod " + n + ")");

        scanner.close();
    }
}
