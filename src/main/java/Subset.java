/**
 * User: alex
 * Date: 2/19/13
 * Time: 2:34 PM
 */
public class Subset {

    public static void main(String[] args)
    {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rQueue = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            rQueue.enqueue(s);
        }

        for(int i=0; i < k; i++)
        {
            System.out.println(rQueue.dequeue());
        }
    }
}
