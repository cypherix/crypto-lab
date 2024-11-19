import java.util.Scanner;

public class DiffieHellmanKeyExchange {

    // Calculate (base^exponent) % mod using modular exponentiation
    public static long modularExponentiation(long base, long exponent, long mod) {
        long result = 1;
        base = base % mod; // Ensure base < mod

        while (exponent > 0) {
            if ((exponent & 1) == 1) { // If exponent is odd
                result = (result * base) % mod;
            }
            base = (base * base) % mod; // Square the base
            exponent >>= 1; // Divide exponent by 2
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the prime number (p) and the primitive root (g)
        System.out.println("Enter a prime number (p):");
        long p = scanner.nextLong();

        System.out.println("Enter a primitive root modulo p (g):");
        long g = scanner.nextLong();

        // Input private keys for User 1 and User 2
        System.out.println("User 1, enter your private key (a):");
        long a = scanner.nextLong();

        System.out.println("User 2, enter your private key (b):");
        long b = scanner.nextLong();

        // Compute public keys
        long publicKey1 = modularExponentiation(g, a, p); // A = g^a mod p
        long publicKey2 = modularExponentiation(g, b, p); // B = g^b mod p

        System.out.println("\nPublic key of User 1 (A): " + publicKey1);
        System.out.println("Public key of User 2 (B): " + publicKey2);

        // Compute shared secret keys
        long sharedKey1 = modularExponentiation(publicKey2, a, p); // K1 = B^a mod p
        long sharedKey2 = modularExponentiation(publicKey1, b, p); // K2 = A^b mod p

        System.out.println("\nShared secret key computed by User 1: " + sharedKey1);
        System.out.println("Shared secret key computed by User 2: " + sharedKey2);

        // Verify that both keys match
        if (sharedKey1 == sharedKey2) {
            System.out.println("\nKey exchange successful! Shared secret key: " + sharedKey1);
        } else {
            System.out.println("\nKey exchange failed. Keys do not match.");
        }

        scanner.close();
    }
}

