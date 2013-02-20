import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * User: alex
 * Date: 2/19/13
 * Time: 2:30 PM
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int used = 0;
    private int numberOfFilledElements = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[1];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return used == 0;
    }

    // return the number of items on the queue
    public int size() {
        return used;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new NullPointerException("item");

        if(numberOfFilledElements == items.length)
            resize(numberOfFilledElements * 2);

        ++used;
        items[numberOfFilledElements++] = item;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("empty");

        int j = 0;
        do {
            j =  StdRandom.uniform(numberOfFilledElements);
        } while ( items[j] == null);

        --used;

        Item item = items[j];

        items[j] = null;

        if(used > 0 && used == items.length / 4)
            resize(items.length / 2);

        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("empty");
        int j;

        do {
            j =  StdRandom.uniform(items.length);
        } while (items[j] == null);

        return items[j];
    }

    private void resize(int size) {
        Item[] newItems = (Item[]) new Object[size];

        numberOfFilledElements = 0;

        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                continue;
            }

            newItems[numberOfFilledElements++] = items[i];
        }

        items = newItems;
    }

    public Iterator<Item> iterator()   // return an independent iterator over items in random order
    {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int n = 0;
        private Item[] data = (Item[]) new Object[used];

        public RandomizedQueueIterator() {

            int j = 0;

            for (int i = 0; i < items.length; i++) {

                if (items[i] == null)
                    continue;

                data[j] = items[i];

                int r = StdRandom.uniform(j + 1);

                Item tmp = data[j];
                data[j] = data[r];
                data[r] = tmp;

                j++;
            }
        }

        public boolean hasNext() {
            return n < data.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {

            if (!hasNext())
                throw new NoSuchElementException();

            return data[n++];
        }
    }
}
