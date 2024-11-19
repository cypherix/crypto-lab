import java.util.Scanner;

public class EulersTheorem {

    // Function to perform modular exponentiation
    // It returns (base^exp) % mod
    public static long modExp(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;  // Handle the case when base is larger than mod
        
        while (exp > 0) {
            // If exp is odd, multiply base with result
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }

            // Square the base
            base = (base * base) % mod;

            // Right shift exp (i.e., divide by 2)
            exp = exp >> 1;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input for base and modulus
        System.out.println("Enter the base (a): ");
        int base = sc.nextInt();

        System.out.println("Enter the exponent (power): ");
        int exponent = sc.nextInt();

        System.out.println("Enter the modulus (n): ");
        int mod = sc.nextInt();

        // Check if base and mod are relatively prime
        if (gcd(base, mod) != 1) {
            System.out.println("The base and modulus are not relatively prime. Euler's theorem does not apply.");
            sc.close();
            return;
        }

        // Calculate Euler's Totient function phi(n)
        long phiN = totient(mod);
        System.out.println("Euler's Totient function phi(" + mod + ") = " + phiN);

        // Reduce the exponent using Euler's theorem (a^phi(n) ≡ 1 (mod n))
        long reducedExponent = exponent % phiN;

        // Compute the result (base^reducedExponent) % mod
        long result = modExp(base, reducedExponent, mod);

        System.out.println(base + "^" + exponent + " mod " + mod + " = " + result);
        sc.close();
    }

    // Function to calculate GCD using Euclidean algorithm
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to calculate Euler's Totient function phi(n)
    public static long totient(int n) {
        long result = n; // Initialize result as n

        // Check if n is divisible by any prime factor
        for (int p = 2; p * p <= n; p++) {
            // Check if p is a factor of n
            if (n % p == 0) {
                // If yes, then divide n by p repeatedly and apply the formula
                while (n % p == 0) {
                    n /= p;
                }
                result -= result / p;
            }
        }

        // If n is still greater than 1, then n is prime
        if (n > 1) {
            result -= result / n;
        }

        return result;
    }
}
