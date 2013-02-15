

/**
 * User: alex
 * Date: 2/12/13
 * Time: 1:58 PM
 */
public class Percolation {

    private int size;
    private int[] grid;
    private WeightedQuickUnionUF uf;
    private int virtualTopPos;
    private int virtualBottomPos;

    public Percolation(int N) {
        if(N < 1){
            throw new IllegalArgumentException("N is invalid");
        }

        this.size = N;
        grid = new int[N*N];

        uf = new WeightedQuickUnionUF(N*N+2);
        virtualTopPos = N*N;
        virtualBottomPos = N*N+1;

        for (int j = 1; j <= N; j++) {
            uf.union(mapIndex(1, j), virtualTopPos);
            uf.union(mapIndex(N, j), virtualBottomPos);
        }
    }

    private int mapIndex(int i, int j) {
        checkInBounds(i, j);
        return size *(i-1) + (j-1);
    }

    private void checkInBounds(int i, int j) {
        if (i < 1 || j < 1 || i > size || j > size)
            throw new IndexOutOfBoundsException("Index out of bounds");
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        checkInBounds(i, j);
        if (grid[mapIndex(i, j)] != 0) { return true; }
        return false;

    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        checkInBounds(i, j);
        if (isOpen(i, j) && uf.connected(virtualTopPos, mapIndex(i, j))) { return true; }
        return false;

    }

    public void open(int i, int j) {
        checkInBounds(i, j);
        this.grid[mapIndex(i, j)] = 1;

        if (i > 1 && isOpen(i-1, j))
        { uf.union(mapIndex(i, j),
                mapIndex(i - 1, j)); }
        if (i < size && isOpen(i+1, j))
        { uf.union(mapIndex(i, j),
                mapIndex(i + 1, j)); }
        if (j > 1 && isOpen(i, j-1))
        { uf.union(mapIndex(i, j),
                mapIndex(i, j - 1)); }
        if (j < size && isOpen(i, j+1))
        { uf.union(mapIndex(i, j),
                mapIndex(i, j + 1)); }

    }

    public boolean percolates() {

        if (size < 3)
        {
            for(int i = 1; i <= size; i++)
            {
                if(isFull(size, i)) {
                    return true;
                }
            }
            return false;
        }
        return uf.connected(virtualTopPos, virtualBottomPos);
    }
}
