import java.math.BigInteger;
import java.util.Scanner;

public class RSAAlgorithm {

    // Compute gcd using the Euclidean algorithm
    public static BigInteger gcd(BigInteger a, BigInteger b) {
        while (!b.equals(BigInteger.ZERO)) {
            BigInteger temp = b;
            b = a.mod(b);
            a = temp;
        }
        return a;
    }

    // Compute modular exponentiation: (base^exponent) % mod
    public static BigInteger modularExponentiation(BigInteger base, BigInteger exponent, BigInteger mod) {
        BigInteger result = BigInteger.ONE;
        base = base.mod(mod);

        while (exponent.compareTo(BigInteger.ZERO) > 0) {
            if (exponent.mod(BigInteger.TWO).equals(BigInteger.ONE)) {
                result = (result.multiply(base)).mod(mod);
            }
            base = (base.multiply(base)).mod(mod);
            exponent = exponent.divide(BigInteger.TWO);
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input prime numbers p and q
        System.out.println("Enter a prime number (p):");
        BigInteger p = scanner.nextBigInteger();

        System.out.println("Enter another prime number (q):");
        BigInteger q = scanner.nextBigInteger();

        // Calculate n = p * q and phi = (p - 1) * (q - 1)
        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        System.out.println("Enter a public exponent (e) such that 1 < e < phi and gcd(e, phi) = 1:");
        BigInteger e = scanner.nextBigInteger();

        // Validate that gcd(e, phi) = 1
        while (!gcd(e, phi).equals(BigInteger.ONE)) {
            System.out.println("Invalid e. Enter a value such that gcd(e, phi) = 1:");
            e = scanner.nextBigInteger();
        }

        // Calculate the private key d (modular multiplicative inverse of e mod phi)
        BigInteger d = e.modInverse(phi);

        System.out.println("\nPublic Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");

        // Input plaintext
        System.out.println("\nEnter the plaintext (as a number):");
        BigInteger plaintext = scanner.nextBigInteger();

        // Encrypt: ciphertext = (plaintext^e) mod n
        BigInteger ciphertext = modularExponentiation(plaintext, e, n);
        System.out.println("Encrypted Text (Ciphertext): " + ciphertext);

        // Decrypt: decryptedText = (ciphertext^d) mod n
        BigInteger decryptedText = modularExponentiation(ciphertext, d, n);
        System.out.println("Decrypted Text (Plaintext): " + decryptedText);

        scanner.close();
    }
}
