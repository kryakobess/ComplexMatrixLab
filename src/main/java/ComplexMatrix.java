import java.util.Arrays;
import java.util.Objects;

public class ComplexMatrix {
    private int rows;
    private int columns;
    private Complex[][] matrix;

    private class IncorrectMatrixSizeException extends Throwable{};

    public ComplexMatrix(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new Complex[rows][columns];
    }
    public ComplexMatrix(int n){
        this(n,n);
    }
    public <T extends Number> ComplexMatrix(int rows, int columns, T[][] filledMatrix){
        this(rows, columns);
        this.fillMatrix(filledMatrix);
    }

    public <T extends Number> void fillMatrix(T[][] filledMatrix) {
        if (filledMatrix instanceof Complex[][]){
            this.setMatrix((Complex[][]) filledMatrix);
        }
        else {
            for (int i = 0; i < this.rows; ++i) {
                System.arraycopy(Complex.toComplexArray(filledMatrix[i]), 0, this.matrix[i], 0, columns);
            }
        }
    }

    public ComplexMatrix add(ComplexMatrix another){
        if (this.rows == another.rows && this.columns == another.columns){
            ComplexMatrix resultMatrix = new ComplexMatrix(this.rows, this.columns);
            for (int i = 0; i < this.rows; ++i){
                for (int j = 0; j < this.columns; ++j){
                    resultMatrix.matrix[i][j] = this.matrix[i][j].add(another.matrix[i][j]);
                }
            }
            return resultMatrix;
        }
        else return null;
    }

    public ComplexMatrix subtract(ComplexMatrix another){
        if (this.rows == another.rows && this.columns == another.columns){
            ComplexMatrix resultMatrix = new ComplexMatrix(this.rows, this.columns);
            for (int i = 0; i < this.rows; ++i){
                for (int j = 0; j < this.columns; ++j){
                    resultMatrix.matrix[i][j] = this.matrix[i][j].subtract(another.matrix[i][j]);
                }
            }
            return resultMatrix;
        }
        else return null;
    }

    public ComplexMatrix multiply(ComplexMatrix another){
        if (this.columns == another.rows){
            ComplexMatrix result = new ComplexMatrix(this.rows, another.columns);
            for (int i = 0; i < this.rows; ++i){
                for (int j = 0; j < another.columns; ++j){
                    Complex sum = new Complex(0);
                    for (int k = 0; k < this.columns; ++k){
                        sum = sum.add(this.matrix[i][k].multiply(another.matrix[k][j]));
                    }
                    result.matrix[i][j] = sum;
                }
            }
            return result;
        }
        else return null;
    }

    public ComplexMatrix transpose(){
        ComplexMatrix result = new ComplexMatrix(this.columns, this.rows);
        for (int i = 0; i < this.rows; ++i){
            for (int j = 0; j < this.columns; ++j){
                result.matrix[j][i] = this.matrix[i][j];
            }
        }
        return result;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Complex[][] getMatrix() {
        return matrix;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setMatrix(Complex[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComplexMatrix that)) return false;
        return getRows() == that.getRows() && getColumns() == that.getColumns() && Arrays.deepEquals(getMatrix(), that.getMatrix());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getRows(), getColumns());
        result = 31 * result + Arrays.hashCode(getMatrix());
        return result;
    }

    @Override
    public String toString() {
        return "ComplexMatrix{" +
                "rows=" + rows +
                ", columns=" + columns +
                ", matrix=" + Arrays.deepToString(matrix) +
                '}';
    }
}
