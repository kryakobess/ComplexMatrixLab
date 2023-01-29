public class Matrix<T extends Number> {
    private int rows;
    private int columns;
    private T[][] matrix;

    public Matrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = (T[][]) new Number[rows][columns];
    }

}
