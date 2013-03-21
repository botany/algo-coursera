import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: alex
 * Date: 3/19/13
 * Time: 2:48 PM
 */
public class PriorityQueueTest {

    @Test
    public void testInsertIntoBinaryHeap() {
        Heap.sort(new Integer[]{88, 12, 64, 50, 61, 85, 60, 92, 95, 31});
    }

    @Test
    public void testBinaryHeapInsert() {
        int[] a = new int[]{87, 82, 73, 59, 77, 24, 30, 34, 51, 12};

        BinaryHeap bh = new BinaryHeap(a);

        bh.add(35);
        bh.add(43);
        bh.add(62);

        System.out.println(bh);

    }

    @Test
    public void testDel() {
        int[] a = new int[]{96, 75, 87, 58, 55, 66, 80, 25, 46, 33};

        BinaryHeap bh = new BinaryHeap(a);

        bh.remove();
        bh.remove();
        bh.remove();
    }
    
    @Test
    public void testBSTInsert() {
        BST bst = new BST();
        bst.put(50, 1);
        bst.put(97, 1);
        bst.put(64, 1);
        bst.put(61, 1);
        bst.put(81, 1);
        bst.put(68, 1);
        bst.put(48, 1);
        bst.put(16, 1);
        bst.put(41, 1);
        bst.put(31, 1);

        Iterator iterator = bst.levelOrder().iterator();
        while(iterator.hasNext())
            System.out.print(iterator.next().toString() + " ");


    }

    @Test
    public void testBSTDel() {
        BST bst = new BST();

        for(String s: "43 24 72 41 70 76 62 92 68 88 66 69".split("\\s+"))
        {
            if(s != null && !s.isEmpty())
                bst.put(Integer.valueOf(s), 1);
        }

        bst.delete(66);
        bst.delete(70);
        bst.delete(72);

        Iterator iterator = bst.levelOrder().iterator();
        while(iterator.hasNext())
            System.out.print(iterator.next().toString() + " ");
    }

    public class BinaryHeap {
        //ArrayList to hold the heap
        List h = new ArrayList();
        public BinaryHeap(){

        }
        //Constructs the heap - heapify
        public BinaryHeap(int[] e) {
            for(int i=0; i<e.length;i++)
            {
                add(e[i]);
            }
        }
        private int intGet(int key){
            return new Integer(h.get(key).toString()).intValue();
        }
        public void add(int key){
            h.add(null);
            int k = h.size()-1;
            while (k>0){
                int parent = (k-1)/2;
                int parentValue = new Integer(h.get(parent).toString()).intValue();
                //MaxHeap -
                //for minheap - if(key > parentValue)
                if(key <= parentValue){
                    break;
                }
                h.set(k,parentValue);
                k=parent;
            }
            h.set(k,key);
        }
        public int getMax()
        {
            return new Integer(h.get(0).toString()).intValue();
        }
        public void percolateUp(int k, int key){
            if(h.isEmpty())
                return ;

            while(k < h.size() /2){
                int child = 2*k + 1; //left child
                if(child < h.size() -1 &&
                        (new Integer(h.get(child).toString()).intValue() <
                                new Integer(h.get(child+1).toString()).intValue()    )){
                    child++;
                }
                if(key >= new Integer(h.get(child).toString()).intValue()){
                    break;
                }
                h.set(k,new Integer(h.get(child).toString()).intValue());
                k=child;
            }
            h.set(k,key);
        }
        public int remove()
        {
            int removeNode = new Integer(h.get(0).toString()).intValue();
            int lastNode = new Integer(h.remove(h.size()-1).toString()).intValue();
            percolateUp(0,lastNode);
            return removeNode;
        }
        public boolean isEmpty()
        {
            return h.isEmpty();
        }
    }
}
