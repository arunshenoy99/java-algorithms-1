public class Selection
{
    public void sort(int[] a)
    {
        int min=0,temp=0;
        for (int i = 0; i < a.length; i++)
        {
            min = i;
            for (int j = i + 1; j < a.length; j++)
            {
                if (a[min] > a[j])
                {
                    min = j;
                }
            }
            temp = a[i];
            a[i] = a[min];
            a[min] = temp;
        }
    }
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, 7, 6, 3};
        Selection s = new Selection();
        s.sort(a);
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
}