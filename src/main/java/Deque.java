import java.util.*;

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
        return new CustomIterator(0);
    }

    private transient Entry<Item> header = new Entry<Item>(null, null, null);

    private transient int modCount = 0;
    private transient int size = 0;

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

    private Entry<Item> addBefore(Item e, Entry<Item> entry) {
        Entry<Item> newEntry = new Entry<Item>(e, entry, entry.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        size++;
        modCount++;
        return newEntry;
    }

    private Item remove(Entry<Item> e) {
        if (e == header)
            throw new NoSuchElementException();

        Item result = e.element;
        e.previous.next = e.next;
        e.next.previous = e.previous;
        e.next = e.previous = null;
        e.element = null;
        size--;
        modCount++;
        return result;
    }

    private class CustomIterator implements ListIterator<Item> {
        private Entry<Item> lastReturned = header;
        private Entry<Item> next;
        private int nextIndex;
        private int expectedModCount = modCount;

        CustomIterator(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("Index: "+index+
                        ", Size: "+size);
            if (index < (size >> 1)) {
                next = header.next;
                for (nextIndex=0; nextIndex<index; nextIndex++)
                    next = next.next;
            } else {
                next = header;
                for (nextIndex=size; nextIndex>index; nextIndex--)
                    next = next.previous;
            }
        }

        public boolean hasNext() {
            return nextIndex != size;
        }

        public Item next() {
            checkForComodification();
            if (nextIndex == size)
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.element;
        }

        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        public Item previous() {
            if (nextIndex == 0)
                throw new NoSuchElementException();

            lastReturned = next = next.previous;
            nextIndex--;
            checkForComodification();
            return lastReturned.element;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex-1;
        }

        public void remove() {
            checkForComodification();
            Entry<Item> lastNext = lastReturned.next;

            try {
                Deque.this.remove(lastReturned);
            }
            catch(NoSuchElementException e)
            {
                throw new UnsupportedOperationException("Error", e);
            }

            if (next==lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = header;
            expectedModCount++;
        }

        public void set(Item e) {
            if (lastReturned == header)
                throw new IllegalStateException();
            checkForComodification();
            lastReturned.element = e;
        }

        public void add(Item e) {
            checkForComodification();
            lastReturned = header;
            addBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }
}
