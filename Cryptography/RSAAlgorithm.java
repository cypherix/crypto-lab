import java.math.BigInteger;
import java.security.SecureRandom;

public class RSAAlgorithm {
    private BigInteger p; // prime 1
    private BigInteger q; // prime 2
    private BigInteger n; // modulus n = p * q
    private BigInteger phi; // phi(n) = (p-1) * (q-1)
    private BigInteger e; // public exponent
    private BigInteger d; // private exponent
    private int bitLength = 1024; // bit length for p and q primes
    private SecureRandom random = new SecureRandom();

    // Constructor to generate keys
    public RSAAlgorithm() {
        // Step 1: Select two large prime numbers, p and q
        p = BigInteger.probablePrime(bitLength, random);
        q = BigInteger.probablePrime(bitLength, random);
        
        // Step 2: Calculate n = p * q
        n = p.multiply(q);
        
        // Step 3: Calculate phi(n) = (p - 1) * (q - 1)
        phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        
        // Step 4: Choose e such that 1 < e < phi(n) and gcd(e, phi(n)) = 1
        e = BigInteger.probablePrime(bitLength / 2, random);
        while (phi.gcd(e).intValue() > 1 && e.compareTo(phi) < 0) {
            e = e.add(BigInteger.ONE);
        }
        
        // Step 5: Calculate d = e^-1 mod phi(n)
        d = e.modInverse(phi);
    }

    // Encryption: C = M^e mod n
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decryption: M = C^d mod n
    public BigInteger decrypt(BigInteger cipherText) {
        return cipherText.modPow(d, n);
    }

    // Get the public key (e, n)
    public BigInteger[] getPublicKey() {
        return new BigInteger[]{e, n};
    }

    // Get the private key (d, n)
    public BigInteger[] getPrivateKey() {
        return new BigInteger[]{d, n};
    }

    // Main function to demonstrate RSA encryption and decryption
    public static void main(String[] args) {
        RSAAlgorithm rsa = new RSAAlgorithm();
        
        String message = "Hello RSA!";
        System.out.println("Original message: " + message);
        
        // Convert the message to a BigInteger for encryption
        BigInteger plainText = new BigInteger(message.getBytes());
        
        // Encrypt the plaintext
        BigInteger cipherText = rsa.encrypt(plainText);
        System.out.println("Encrypted message: " + cipherText);
        
        // Decrypt the ciphertext
        BigInteger decryptedMessage = rsa.decrypt(cipherText);
        String decryptedText = new String(decryptedMessage.toByteArray());
        System.out.println("Decrypted message: " + decryptedText);
    }
}
