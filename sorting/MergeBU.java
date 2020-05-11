import edu.princeton.cs.algs4.Insertion;

public class MergeBU {
    private static Comparable aux[];

    public static void merge(Comparable a[], int lo, int mid, int hi)
    {
        int i =0, j = 0, k = 0;
        for (i = lo; i <= hi; i++)
        {
            aux[i] = a[i];
        }
        i = lo;
        j = mid + 1;
        for(k = lo; k <= hi; k++)
        {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void sort(Comparable a[])
    {
        int N = a.length;
        aux = new Comparable[a.length];
        for (int sz = 1; sz < N; sz = sz + sz)
            for(int lo = 0; lo< N - sz; lo += sz + sz)
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
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