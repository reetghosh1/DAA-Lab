package Algos;
import java.util.*;
public class BinomialCoeff
{
    static int binod(int n, int k)
    {
        int C[][] = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
        {
            for (int j = 0; j <= min(i, k); j++)
            {
                if (j == 0 || j == i)
                {
                    C[i][j] = 1;
                }
                else
                {
                    C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
                }
            }
        }
        return C[n][k];
    }

    static int min(int a, int b)
    {
        if(a<b)
        {
            return a;
        }
        else
        {
            return b;
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter value of n:");
        int n = sc.nextInt();
        System.out.println("Enter value of k:");
        int k = sc.nextInt();
        System.out.println("Value of C(" + n + "," + k + ") is: " + binod(n, k));
    }
}
