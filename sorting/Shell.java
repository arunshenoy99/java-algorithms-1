//Decrement h as needed for making sort subtle
//Use 3x+1 series or sedgewick series

public class Shell
{
    public void sort(int[] a)
    {
        int temp;
        int h = 1;
        while(h < a.length/3) 
        {
            h = 3*h + 1;
        }
        while(h >= 1)
        {
            for (int i = h - 1; i < a.length; i++)
            {
                for (int j = i; j >= h; j = j - h)
                {
                    if (a[j-h] > a[j])
                    {
                        temp = a[j];
                        a[j] = a[j-h];
                        a[j-h] = temp;
                    }
                }
            }
            h = h/3;
        }
    }
    public static void main(String[] args)
    {
        int[] a = {1, 2, 3, 7, 0, 3};
        Shell s = new Shell();
        s.sort(a);
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }
}