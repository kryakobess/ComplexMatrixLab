import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class ComplexTest {
    @Test
    public void complexTest(){
        Complex num = new Complex(13,2);
        System.out.println(num.asExpForm());
        Complex n1 = new Complex(-18, -50);
        Complex res = new Complex(-134, -686);
        System.out.println(num.divide(n1));
        Assert.assertEquals(res, num.multiply(n1));
    }

    @Test
    public void matrixTest(){
        BigInteger bigInteger = new BigInteger(String.valueOf(3232323));
        Number number = new Complex(3,14);


    }
}
