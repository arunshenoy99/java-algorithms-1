import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int[][] grid;
    private int[] status;
    private final WeightedQuickUnionUF uf;
    private final int n;
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n)
    {
        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }
        int count = 0;
        this.n = n;
        grid = new int[n+1][n+1];
        status = new int[(n*n)+1];
        uf = new WeightedQuickUnionUF((n*n)+2);
        for (int i = 1; i <= n; i++) 
        {
            for (int j = 1; j <= n; j++)
            {
                grid[i][j] = ++count;
                status[count] = 0;
                if (i == 1)
                {
                    uf.union(0, grid[i][j]);
                }
                if (i == n)
                {
                    uf.union(n*n+1, grid[i][j]);
                }
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
        if (row < 1 || col < 1 || row > n || col > n)
        {
            throw new IllegalArgumentException();
        }
        if (status[grid[row][col]] == 1)
        {
            return;
        }
        int site = grid[row][col];
        if (row == 1 && col == 1)
        {
            openright(row, col, site);
            openbottom(row, col, site);   
        } 
        else if (row == 1 && col == n)
        {
            openleft(row, col, site);
            openbottom(row, col, site);
        }
        else if (col == 1 && row == n)
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
            openright(row, col, site);
            openbottom(row, col, site);
        }
        else if (row == n && col == n)
        {
            opentop(row, col, site);
            openleft(row, col, site);
        }
        else if (row == n)
        {
            opentop(row, col, site);
            openleft(row, col, site);
            openright(row, col, site);
        }
        else if (col == n)
        {
            opentop(row, col, site);
            openleft(row, col, site);
            openbottom(row, col, site);
        }
        else 
        {
            opentop(row, col, site);
            openleft(row, col, site);
            openright(row, col, site);
            openbottom(row, col, site);
        }
        status[grid[row][col]] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (row < 1 || col < 1 || row > n || col > n)
        {
            throw new IllegalArgumentException();
        }
        return status[grid[row][col]] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (row < 1 || col < 1 || row > n || col > n)
        {
            throw new IllegalArgumentException();
        }
        if (!isOpen(row, col))
        {
            return false;
        }
        int site = grid[row][col];
        return uf.find(site) == 0;
    }

    // returns the number of open sites
    public int numberOfOpenSites()
    {
        int count = 0;
        for (int i = 1; i <= n*n; i++)
        {
            if (status[i] == 1)
            {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates()
    {
        if (n == 1)
        {
            return true;
        }
        return uf.find(0) == uf.find((n*n)+1);
    }
}