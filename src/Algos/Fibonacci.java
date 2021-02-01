package Algos;
import java.util.*;
public class Fibonacci
{
    static void fib(int n)
    {
        int f[] = new int[n+2]; // 1 extra to handle case, n = 0
        int i;

        f[0] = 0;
        f[1] = 1;

        for (i = 2; i <= n; i++)
        {
            f[i] = f[i-1] + f[i-2];
        }

        for(int j=0;j<=n;j++)
        {
            System.out.println(f[j]);
        }
    }

    public static void main (String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number to find Fibonacci up to: ");
        int n = sc.nextInt();
        fib(n);
    }
}
