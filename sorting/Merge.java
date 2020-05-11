import edu.princeton.cs.algs4.Insertion;

public class Merge {
    private static Comparable aux[];
    private static int CUTOFF = 7;

    public static void merge(Comparable a[], Comparable aux[], int lo, int mid, int hi)
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
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }

    private static void sort(Comparable a[], Comparable aux[], int lo, int hi)
    {
        if (hi <= lo + CUTOFF - 1)
        {
            Insertion.sort(a, lo, hi);  //Better for smaller subarrays
        }
        if (lo >= hi) return;
        int mid = lo + (hi - lo)/2;
        sort (a, aux, lo, mid);
        sort (a, aux, mid + 1, hi);
        if (a[mid].compareTo(a[mid + 1]) <= 0) return; //Biggest element in first half is <= smallest element in second half then already sorted
        merge(a, aux, lo, mid, hi);
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