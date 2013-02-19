import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * User: alex
 * Date: 2/19/13
 * Time: 2:30 PM
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private LinkedList<Item> items;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
       items = new LinkedList<Item>();
    }

    // is the queue empty?
    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    // return the number of items on the queue
    public int size()
    {
        return items.size();
    }

    // add the item
    public void enqueue(Item item)
    {
        items.add(item);
    }

    // delete and return a random item
    public Item dequeue()
    {
        if(isEmpty()) throw new NoSuchElementException("empty");

        Item removedItem = items.remove(StdRandom.uniform(items.size() - 1));

        return removedItem;
    }

    // return (but do not delete) a random item
    public Item sample()
    {
        if(isEmpty()) throw new NoSuchElementException("empty");

        Item item = items.get(StdRandom.uniform(items.size() - 1));

        return item;
    }

    public Iterator<Item> iterator()   // return an independent iterator over items in random order
    {
        return items.iterator();
    }
}
