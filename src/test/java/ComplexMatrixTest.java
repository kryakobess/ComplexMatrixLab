import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class ComplexMatrixTest {

    @Test
    public void creationTest(){
        ComplexMatrix matrix = new ComplexMatrix(5);
        for (int i = 0; i < 5; ++i){
            for (int j = 0; j < 5; ++j){
                Assert.assertNull(matrix.getMatrix()[i][j]);
            }
        }
        Integer[][] integers = {{12,55,22},{2,2,2},{55,5,5}};
        matrix = new ComplexMatrix(3,3,integers);
        for (int i = 0; i < 3; ++i){
            Assert.assertTrue(Arrays.equals(matrix.getMatrix()[i],(Complex.toComplexArray(integers[i]))));
        }
        Complex[][] complexes = {{new Complex(5,3), new Complex(1,-90)},
                                 {new Complex(4,77), new Complex(5)}};
        matrix = new ComplexMatrix(2,2,complexes);
        Assert.assertTrue(Arrays.deepEquals(matrix.getMatrix(), complexes));
    }

    @Test
    public void addMatrixTest(){
        Integer[][] integers = {{1,1,1},{5,5,5},{9,9,9}};
        ComplexMatrix matrix = new ComplexMatrix(3,3, integers);
        Complex[][] complexes = {
                {new Complex(5.15, 9), new Complex(13,12), new Complex(2,3)},
                {new Complex(7,6), new Complex(0,-4), new Complex(9.2, -7)},
                {new Complex(3, 1), new Complex(-5,-5), new Complex(1)}
        };
        Complex[][] resComp = {
                {new Complex(6.15, 9), new Complex(14,12), new Complex(3,3)},
                {new Complex(12,6), new Complex(5,-4), new Complex(14.2, -7)},
                {new Complex(12, 1), new Complex(4,-5), new Complex(10)}
        };
        ComplexMatrix result = new ComplexMatrix(3,3, resComp);
        Assert.assertEquals(result, matrix.add(new ComplexMatrix(3,3, complexes)));
    }
    @Test
    public void multiplyMatrixTest(){
        Integer[][] first = {{12,3,4},{15,3,3}, {2,2,2},{4,4,4}};
        Integer[][] second = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        ComplexMatrix firstMatrix = new ComplexMatrix(4,3, first);
        ComplexMatrix secondMatrix = new ComplexMatrix(3,4,second);
        Integer[][] result = {{63,82,101,120},{57,78,99,120},{30,36,42,48},{60,72,84,96}};
        ComplexMatrix res = new ComplexMatrix(4,4, result);
        Assert.assertEquals(res, firstMatrix.multiply(secondMatrix));
    }

    @Test
    public void transposeMatrixTest(){
        Complex[][] complexes = {
                {new Complex(5.15, 9), new Complex(13,12), new Complex(2,3)},
                {new Complex(7,6), new Complex(0,-4), new Complex(9.2, -7)},
        };
        Complex[][] res = {
                {new Complex(5.15, 9), new Complex(7,6)},
                {new Complex(13,12), new Complex(0,-4)},
                {new Complex(2,3), new Complex(9.2, -7)},
        };
        ComplexMatrix resMat = new ComplexMatrix(3,2, res);
        ComplexMatrix matrix = new ComplexMatrix(2,3, complexes);
        Assert.assertNotEquals(matrix.transpose(), resMat);
    }
}
