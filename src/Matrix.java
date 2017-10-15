/**
 * Created by ASUS on 15/10/2017.
 */
public class Matrix {
    public int cols;
    public int rows;
    public int[][] grid;// row , col

    public Matrix(int cols, int rows, int[][] grid) {
        this.cols = cols;
        this.rows = rows;
        this.grid = grid.clone();
    }
}
