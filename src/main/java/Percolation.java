

/**
 * User: alex
 * Date: 2/12/13
 * Time: 1:58 PM
 */
public class Percolation {

    private int[][] grid;
    private WeightedQuickUnionUF uf;
    private int size;
    private int gridSize;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N < 1)
            throw new IllegalArgumentException("Wrong N");

        this.grid = new int[N][N];
        this.size = N;
        this.gridSize = N * N + 2;
        this.uf = new WeightedQuickUnionUF(gridSize);

        for (int i = 1; i <= N; i++) {
            uf.union(0, i);
            uf.union(gridSize - 1, gridSize - i - 1);
        }
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        validateArgs(i, j);

        // open only if not opened
        if (getSite(i, j) == 0) {

            grid[i - 1][j - 1] = 1;

            if (i != size && getSite(i + 1, j) == 1)
                uf.union(mapIndex(i, j), mapIndex(i + 1, j));

            if (i != 1 && getSite(i - 1, j) == 1)
                uf.union(mapIndex(i, j), mapIndex(i - 1, j));

            if (j != size && getSite(i, j + 1) == 1)
                uf.union(mapIndex(i, j), mapIndex(i, j + 1));

            if (j != 1 && getSite(i, j - 1) == 1)
                uf.union(mapIndex(i, j), mapIndex(i, j - 1));

            unionIfPercolation(i, j);
        }
    }

    private void validateArgs(int i, int j) {
        if (i > size || i < 1 || j > size || j < 1) {
            throw new IndexOutOfBoundsException("i or j is out of border");
        }
    }

    // returns address in the union array
    private int mapIndex(int i, int j) {
        return (i - 1) * size + j;
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validateArgs(i, j);
        return (getSite(i, j) == 1 ? true : false);
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validateArgs(i, j);
        if (isOpen(i, j) && uf.connected(0, mapIndex(i, j))) {
            return true;
        }

        return false;
    }

    private void unionIfPercolation(int i, int j) {
        if (isFull(i, j)) {
            for (int x = 1; x <= size; x++) {
                if (getSite(size, x) == 1 && isFull(size, x)) {
                    uf.union(mapIndex(size, x), gridSize - 1);
                }
            }
        }
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, gridSize - 1);
    }


    private int getSite(int i, int j) {
        return grid[i - 1][j - 1];
    }
}
