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
        for (int i = 0; i < this.rows; ++i) {
            System.arraycopy(Complex.toComplexArray(filledMatrix[i]), 0, this.matrix[i], 0, columns);
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
            for (int i = 0; i < rows; ++i){
                for (int j = 0; j < another.columns; ++j){
                    j++;
                }
            }
            return null;
        }
        else return null;
    }


}
