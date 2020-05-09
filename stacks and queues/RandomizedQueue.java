import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N;
    private Item s[];

    public RandomizedQueue()
    {
        N = 0;
        s = (Item[]) new Object[1];
    }

    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private int i = N;
        private int[] order;

        public RandomizedQueueIterator()
        {
            order = new int[i];
            for (int j = 0; j < i; j++)
            {
                order[j] = j;
            }
            StdRandom.shuffle(order);
        }
        public boolean hasNext()
        {
            return i > 0;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Item next()
        {
            if (! hasNext())
            {
                throw new NoSuchElementException();
            }
            return s[order[--i]];
        }
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++)
        {
            copy[i] = s[i];
        }
        s = copy;
    }

    public int size()
    {
        return N;
    }

    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        if (N == s.length)
        {
            resize(s.length * 2);
        }
        s[N++] = item;
    }

    public Item dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        int r = StdRandom.uniform(N);
        Item item = s[r];
        s[r] = s[N - 1];
        s[--N] = null;
        if (N > 0 && N == s.length/4)
        {
            resize(s.length/2);
        }
        return item;
    }

    public Item sample()
    {
        int r = StdRandom.uniform(N);
        return s[r];
    }

    public static void main(String[] args)
    {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();

        System.out.println("IS EMPTY:" + rq.isEmpty());

        for (int i = 0; i <= 10; i++)
        {
            rq.enqueue(i);
            System.out.println("RQ SIZE:" + rq.size());
        }

        System.out.println("Sample element");

        System.out.println(rq.sample());

        for (Integer i : rq)
        {
            System.out.println("ITERATOR:" + i);
        }

        for (int i = 0; i <= 10; i++)
        {
            System.out.println(rq.dequeue());
            System.out.println("RQ SIZE:" + rq.size());
        }

        System.out.println("IS EMPTY:" + rq.isEmpty());
    }
}