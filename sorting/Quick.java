import edu.princeton.cs.algs4.StdRandom;

public class Quick
{
    private static int partition(Comparable a[], int lo, int hi)
    {
        int i = lo, j = hi + 1;
        Comparable temp = 0;
        while (true)
        {
            while(a[++i].compareTo(a[lo]) < 0)
                if (i == hi) break;
            while(a[--j].compareTo(a[lo]) > 0)
                if (j == lo) break;             //Redundant check
            if (i >= j)
            {
                break;
            }
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        temp = a[j];
        a[j] = a[lo];
        a[lo] = temp;
        return j;
    }

    public static void sort(Comparable a[])
    {
        StdRandom.shuffle(a);           //Shuffling is needed for good performance
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable a[], int lo, int hi)
    {
        if (lo >= hi) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
    public static void main(String[] args)
    {
        Comparable a[] = {10, 9, 8, 7, 6, 3, 4, 2, 1, 0};
        sort(a);
        for (int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
}