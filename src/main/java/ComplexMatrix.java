import java.util.Arrays;
import java.util.Objects;

public class ComplexMatrix {
    private int rows;
    private int columns;
    private Complex[][] matrix;

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
    /*
     * Fills matrix parameter by setting another Complex matrix or copying the objects of Number Matrix
     */
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
    /*
     * Returns ComplexMatrix object as a result of addition
     * between this ComplexMatrix and another ComplexMatrix
     */
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
    /*
     * Returns ComplexMatrix object as a result of subtraction
     * between this ComplexMatrix and another ComplexMatrix
     */
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
    /*
     * Returns determinant of particular ComplexMatrix as a Complex object
     */
    public Complex determinant(){
        if (this.rows != this.columns) throw new ArithmeticException("Cannot calculate determinant of not squared matrix");
        else {
            Complex[][] matrix = new Complex[rows][rows];
            for (int i = 0; i < rows; ++i){
                System.arraycopy(this.matrix[i], 0, matrix[i], 0, rows);
            }
            double EPS = 1E-9;

            Complex det = new Complex(1);
            for (int i = 0; i < rows; ++i){
                int k = i;
                for (int j = i+1; j < rows; ++j){
                    if (matrix[j][i].getAbs() > matrix[k][i].getAbs()){
                        k = j;
                    }
                }
                if (matrix[k][i].getAbs() < EPS){
                    det = new Complex(0);
                    break;
                }
                Complex[] tmp = matrix[i];
                matrix[i] = matrix[k];
                matrix[k] = tmp;
                if (i != k){
                    det = det.multiply(new Complex(-1));
                }
                det = det.multiply(matrix[i][i]);
                for (int j = i+1; j < rows; ++j){
                    matrix[i][j] = matrix[i][j].divide(matrix[i][i]);
                }
                for (int j = 0; j < rows; ++j){
                    if (j != i && matrix[j][i].getAbs() > EPS){
                        for (int l = i +1; l < rows; ++l){
                            matrix[j][l] = matrix[j][l].subtract(matrix[i][l].multiply(matrix[j][i]));
                        }
                    }
                }
            }
            return det;
        }
    }
    /*
     * Returns ComplexMatrix object as a result of multiplication
     * between this ComplexMatrix and another ComplexMatrix
     */
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
    /*
     * Returns transposed version of ComplexMatrix
     */
    public ComplexMatrix transpose(){
        ComplexMatrix result = new ComplexMatrix(this.columns, this.rows);
        for (int i = 0; i < this.rows; ++i){
            for (int j = 0; j < this.columns; ++j){
                result.matrix[j][i] = this.matrix[i][j];
            }
        }
        return result;
    }
    /*
     * Returns rows parameter
     */
    public int getRows() {
        return rows;
    }
    /*
     * Returns columns parameter
     */
    public int getColumns() {
        return columns;
    }
    /*
     * Returns matrix parameter
     */
    public Complex[][] getMatrix() {
        return matrix;
    }
    /*
     * Sets rows parameter
     */
    public void setRows(int rows) {
        this.rows = rows;
    }
    /*
     * Sets columns parameter
     */
    public void setColumns(int columns) {
        this.columns = columns;
    }
    /*
     * Sets matrix parameter
     */
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
        StringBuilder result = new StringBuilder("ComplexMatrix\n" + "rows=" + rows + ", columns=" + columns + "\n");
        for (int i = 0; i < rows; ++i){
            for (int j = 0; j < columns; ++j){
                result.append(matrix[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}
