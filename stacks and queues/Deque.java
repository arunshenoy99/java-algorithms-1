import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item>
{
    private Node first, last;
    private int N;
    
    private class Node
    {
        Item item;
        Node prev;
        Node next;
    }

    public Deque()
    {
        N = 0;
        first = null;
        last = null;
    }

    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }
    private class DequeIterator implements Iterator<Item>
    {
        private Node current = first;
        
        public boolean hasNext()
        {
            return current != null;
        }

        public Item next()
        {
            if (! hasNext())
            {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    public boolean isEmpty()
    {
        return N == 0;
    }

    public int size()
    {
        return N;
    }

    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.prev = null;
        if (isEmpty())
        {
            last = first;
        }
        else
        {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        N++;
    }

    public void addLast(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty())
        {
            first = last;
        }
        else
        {
            oldLast.next = last;
            last.prev = oldLast;
        }
        N++;
    }

    public Item removeFirst()
    {
        Item item = first.item;
        first = first.next;
        N--;
        if (isEmpty())
        {
            last = first;
        }
        else
        {
            first.prev = null;
        }
        return item;
    }

    public Item removeLast()
    {
        Item item = last.item;
        last = last.prev;
        N--;
        if (isEmpty())
        {
            first = last;
        }
        else
        {
            last.next = null;
        }
        return item;
    }
    public static void main(String[] args) {
        Deque<Integer> deck = new Deque<Integer>();

        System.out.println("IS EMPTY: " + deck.isEmpty());

        for (int i = 0; i < 10; i++) {
            deck.addFirst(i);
            System.out.println("SIZE: " + deck.size());
            System.out.println("IS EMPTY: " + deck.isEmpty());
        }

        System.out.println("Elements 0-9 added. We should have seen 1 to 10 printed");

        for (Integer i : deck) {
            System.out.println(i);
        }

        System.out.println("Finished iterating over the iterator. Elements should appear from 9 to 0.");

        for (int i = 0; i < 10; i++) {
            System.out.println(deck.removeLast());
            System.out.println("IS EMPTY: " + deck.isEmpty());
            System.out.println("Deck size: " + deck.size());
        }

        System.out.println("Elements 0-9 removed. They should appear from 0 to 9.");

        for (int i = 0; i < 10; i++) {
            deck.addLast(i);
            System.out.println("IS EMPTY: " + deck.isEmpty());
            System.out.println("Deck size: " + deck.size());
        }

        System.out.println("Elements 0-9 added.");

        for (Integer i : deck) {
            System.out.println(i);
        }

        System.out.println("Finished iterating over the iterator. Elements should appear from 0 to 9");

        for (int i = 0; i < 10; i++) {
            System.out.println(deck.removeFirst());
            System.out.println("IS EMPTY: " + deck.isEmpty());
            System.out.println("Deck size: " + deck.size());
        }

        System.out.println("Elements 0-9 removed. Elements should appear from 0 to 9");

    }
}