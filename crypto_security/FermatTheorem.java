import java.util.Scanner;

public class FermatTheorem {

    // Function to calculate gcd using Euclidean algorithm
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
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

        // Input: a and p (where p should be prime)
        System.out.print("Enter a number (a): ");
        int a = scanner.nextInt();

        System.out.print("Enter a prime number (p): ");
        int p = scanner.nextInt();

        // Check if gcd(a, p) = 1
        if (gcd(a, p) != 1) {
            System.out.println("a and p are not coprime. Fermat's Little Theorem does not apply.");
            return;
        }

        // Fermat's Little Theorem: a^(p-1) ≡ 1 (mod p)
        int result = modExp(a, p - 1, p);
        System.out.println(a + "^" + (p - 1) + " ≡ " + result + " (mod " + p + ")");

        scanner.close();
    }
}
