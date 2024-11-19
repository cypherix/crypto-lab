import java.util.Scanner;

public class ChineseRemainderTheorem {

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static long modInverse(long a, long m) {
        long m0 = m, t, q;
        long x0 = 0, x1 = 1;

        if (m == 1) {
            return 0;
        }


        while (a > 1) {
            q = a / m;
            t = m;

            m = a % m;
            a = t;                                          

            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) {
            x1 += m0;
        }

        return x1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Enter value for a1: ");
        int a1 = sc.nextInt();
        System.out.println("Enter value for m1: ");
        int m1 = sc.nextInt();

        System.out.println("Enter value for a2: ");
        int a2 = sc.nextInt();
        System.out.println("Enter value for m2: ");
        int m2 = sc.nextInt();

        System.out.println("Enter value for a3: ");
        int a3 = sc.nextInt();
        System.out.println("Enter value for m3: ");
        int m3 = sc.nextInt();

        long M = (long) m1 * m2 * m3;


        long M1 = M / m1;
        long M2 = M / m2;
        long M3 = M / m3;


        long X1 = modInverse(M1, m1);
        long X2 = modInverse(M2, m2);
        long X3 = modInverse(M3, m3);


        long x = (a1 * X1 * M1 + a2 * X2 * M2 + a3 * X3 * M3) % M;


        if (x < 0) {
            x += M;
        }

        System.out.println("The value of x is: " + x);
        sc.close();
    }
}
