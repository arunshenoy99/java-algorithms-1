import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.StdIn;

public class Percolation {

    private int grid[][];
    private int status[][];
    private WeightedQuickUnionUF uf;
    private int n = 0;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n<=0)
        {
            throw new IllegalArgumentException();
        }
        this.n = n;
        uf = new WeightedQuickUnionUF((n*n)+2);
        int count = 0;
        grid = new int[n+1][n+1];
        status = new int[n+1][n+1];
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=n;j++)
            {
                grid[i][j] = ++count;
                status[i][j] = 0;
                if (i==1)
                {
                    uf.union(0, grid[i][j]);
                }
                // if (i==n)
                // {
                //     uf.union(n+1, grid[i][j]);
                // }
            }
        }
    }

    private void openleft(int row, int col, int site)
    {
        int leftsite = col-1;
        if (isOpen(row, leftsite))
        {
            uf.union(site, grid[row][leftsite]);
        }
    }

    private void openright(int row, int col, int site)
    {
        int rightsite = col+1;
        if (isOpen(row, rightsite))
        {
            uf.union(site, grid[row][rightsite]);
        } 
    }

    private void opentop(int row, int col, int site)
    {
        int topsite = row-1;
        if (isOpen(topsite, col))
        {
            uf.union(site, grid[topsite][col]);
        } 
    }

    private void openbottom(int row, int col, int site)
    {
        int bottomsite = row+1;
        if (isOpen(bottomsite, col))
        {
            uf.union(site, grid[bottomsite][col]);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if (status[row][col] == 1)
        {
            return;
        }
        int site = grid[row][col];
        if (row<1 || col<1)
        {
            throw new IllegalArgumentException();
        }
        if (row == 1 && col==1)
        {
            openright(row, col, site);
            openbottom(row, col, site);   
        } 
        else if (row == 1 && col == n)
        {
            openleft(row, col, site);
            openbottom(row, col, site);
        }
        else if(col == 1 && row == n)
        {
            openright(row, col, site);
            opentop(row, col, site);
        }
        else if (row == 1) {
            openleft(row, col, site);
            openright(row, col, site);
            openbottom(row, col, site);
        }
        else if (col == 1)
        {
            opentop(row, col, site);
            openbottom(row, col, site);
            openright(row, col, site);
        }
        else if (row == n && col == n)
        {
            openleft(row, col, site);
            openleft(row, col, site);
        }
        // else if (row==n && col==1)
        // {
        //     opentop(row, col, site);
        //     openright(row, col, site);
        // }
        // else if (col == n && row ==1)
        // {

        // }
        else if (row == n)
        {
            opentop(row, col, site);
            openright(row, col, site);
            openleft(row, col, site);
        }
        else if (col == n)
        {
            openleft(row, col, site);
            opentop(row, col, site);
            openbottom(row, col, site);
        }
        else 
        {
            openleft(row, col, site);
            opentop(row, col, site);
            openbottom(row, col, site);
            openright(row, col, site);
        }
        status[row][col] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (row<1 || col<1)
        {
            throw new IllegalArgumentException();
        }
        return status[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (row<1 || col<1)
        {
            throw new IllegalArgumentException();
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        int count = 0;
        for(int i=1;i<=n;i++)
        {
            for(int j=1; j<=n; j++)
            {
                if (status[i][j] == 1)
                {
                    count++;
                }
            }
        }
        System.out.println(count);
        return count;
    }

    // does the system percolate?
    public boolean percolates()
    {
        for (int i=1;i<=n;i++)
        {
            // System.out.println(uf.find(grid[n][i]));
            if (uf.find(grid[n][i]) == 0)
            {
                return true;
            }
        }
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 3;
        int row=0,col=0;
        Percolation p = new Percolation(n);
        while(!p.percolates())
        {
            row = StdRandom.uniform(1, n+1);
            col = StdRandom.uniform(1, n+1);
            System.out.println(row+","+col);
            p.open(row, col);
            System.out.println(p.numberOfOpenSites());
        }
    }
}