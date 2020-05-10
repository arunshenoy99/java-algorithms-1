public class Insertion
{
    public void sort(int[] a)
    {
        int temp;
        for (int i = 0; i < a.length; i++)
        {
            for (int j = i; j > 0; j--)
            {
                if (a[j-1] > a[j])
                {
                    temp = a[j];
                    a[j] = a[j-1];
                    a[j-1] = temp;
                }
            }
        }
    }
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, 7, 6, 3};
        Insertion s = new Insertion();
        s.sort(a);
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
}