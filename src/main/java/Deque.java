import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * User: alex
 * Date: 2/19/13
 * Time: 12:52 PM
 */
public class Deque<Item> implements Iterable<Item> {

    private LinkedList<Item> items;

    // construct an empty deque
    public Deque()
    {
        this.items = new LinkedList<Item>();
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return items.isEmpty();
    }

    // return the number of items on the deque
    public int size()
    {
        return items.size();
    }

    // insert the item at the front
    public void addFirst(Item item)
    {
        if(item == null)throw new NullPointerException("item");

        items.push(item);
    }

    // insert the item at the end
    public void addLast(Item item)
    {
        if(item == null)throw new NullPointerException("item");

        items.add(item);
    }

    // delete and return the item at the front
    public Item removeFirst()
    {
        if(isEmpty()) throw new NoSuchElementException("empty");

        Item item = items.removeFirst();

        return item;
    }

    // delete and return the item at the end
    public Item removeLast()
    {
        if(isEmpty()) throw new NoSuchElementException("empty");

        Item item = items.removeLast();

        return item;
    }

    public Iterator<Item> iterator()   // return an iterator over items in order from front to end
    {
        return items.iterator();
    }
}
