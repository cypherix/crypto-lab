import java.util.Scanner;

public class FermatsTheorem {


    public static long modExp(long base, long exp, long mod) {
        long result = 1;
        base = base % mod;  
        
        while (exp > 0) {
              
            if ((exp & 1) == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            
     
            exp = exp >> 1;
        }
        
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter an integer a: ");
        long a = sc.nextLong();

        System.out.println("Enter a prime number p: ");
        long p = sc.nextLong();

        if (a % p == 0) {
            System.out.println("Fermat's Little Theorem does not apply because a is divisible by p.");
        } else {

            long result = modExp(a, p - 1, p);
            if (result == 1) {
                System.out.println("Fermat's Little Theorem holds: a^(p-1) ≡ 1 (mod p)");
            } else {
                System.out.println("Fermat's Little Theorem does not hold for this pair (a, p).");
            }
        }

        sc.close();
    }
}
