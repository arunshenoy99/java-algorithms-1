import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
    
    private final double[] stats;
    private final int trials;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }
        stats = new double[trials];
        this.trials = trials;
        Percolation p;
        int row = 1, col = 1;
        for (int i = 0; i < trials; i++)
       {
           p = new Percolation(n);
           while (!p.percolates())
           { 
               row = StdRandom.uniform(1, n+1);
               col = StdRandom.uniform(1, n+1);
               p.open(row, col);
           }
           stats[i] = ((double) p.numberOfOpenSites())/(n*n);
       }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(stats);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return (mean() - ((1.96*stddev())/Math.sqrt(trials)));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return (mean() + ((1.96*stddev())/Math.sqrt(trials)));
    }

   // test client (see below)
   public static void main(String[] args)
   {
       if (args.length < 2)
       {
           throw new IllegalArgumentException();
       }
       int n = Integer.parseInt(args[0]);
       int trials = Integer.parseInt(args[1]);
       PercolationStats ps = new PercolationStats(n, trials);
       double mean = ps.mean();
       double stddev = ps.stddev();
       double lo = ps.confidenceLo();
       double high = ps.confidenceHi();
       System.out.println("Mean\t\t\t=" + mean);
       System.out.println("stddev\t\t\t="+ stddev);
       System.out.println("95% confidence interval =[ "+lo+", "+high+" ]");
   }
}