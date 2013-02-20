import java.util.*;

/**
 * User: alex
 * Date: 2/19/13
 * Time: 12:52 PM
 */
public class Deque<Item> implements Iterable<Item> {

    private Entry<Item> first;
    private Entry<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {
        size = 0;
        first = null;
        last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) throw new NullPointerException("item");

        Entry<Item> entry = new Entry<Item>(item, first, null);

        if (!isEmpty())
            first.previous = entry;

        first = entry;

        if (last == null) last = first;

        ++size;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) throw new NullPointerException("item");

        Entry<Item> entry = new Entry<Item>(item, null, last);

        if (!isEmpty())
            last.next = entry;

        last = entry;

        if (first == null) first = last;

        ++size;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("empty");

        Item firstElement = first.element;

        Entry<Item> newFirst = first.next;

        if (newFirst != null) newFirst.previous = null;

        if (last == first) last = null;

        first = newFirst;

        --size;

        return firstElement;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("empty");

        Item item = last.element;

        Entry<Item> newLast = last.previous;

        if(newLast != null) newLast.next = null;

        if(last == first) last =  first = null;
        else last = newLast;

        --size;

        return item;
    }

    public Iterator<Item> iterator()   // return an iterator over items in order from front to end
    {
        return new CustomIterator();
    }

    private static class Entry<E> {
        E element;
        Entry<E> next;
        Entry<E> previous;

        Entry(E element, Entry<E> next, Entry<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private class CustomIterator implements Iterator<Item> {

        private Entry<Item> current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            Item item = current.element;

            current = current.next;

            return item;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }
}
