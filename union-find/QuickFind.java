import java.util.Scanner;

public class QuickFind
{
    private int id[];

    public QuickFind(int N)
    {
        id = new int[N];
        for (int i = 0; i < N; i++)
        {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q)  //Find is very fast
    {
        return id[p] == id[q];
    }

    public void union(int p, int q) //Union is too slow 
    {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++)
        {
            if (id[p] == pid)
            {
                id[p] = qid;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the value of N");
        int N = sc.nextInt();
        QuickFind qf = new QuickFind(N);
        while(true)
        {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (!qf.connected(p, q))
            {
                qf.union(p, q);
                System.out.println(p + "->" + q);
            }
        }
    }
}