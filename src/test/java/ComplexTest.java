import org.junit.Assert;
import org.junit.Test;

public class ComplexTest {
    @Test
    public void multiplyComplexTest(){
        Complex num = new Complex(13,2);
        System.out.println(num.asExpForm());
        Complex n1 = new Complex(-18, -50);
        Complex res = new Complex(-134, -686);
        Assert.assertEquals(res, num.multiply(n1));
    }

    @Test
    public void addComplexTest(){
        Complex num = new Complex(54,27);
        Complex n1 = new Complex(11, -50);
        Complex res = new Complex(65, -23);
        Assert.assertEquals(res, num.add(n1));
    }
    @Test
    public void subComplexTest(){
        Complex num = new Complex(54,27);
        Complex n1 = new Complex(11, -50);
        Complex res = new Complex(43, 77);
        Assert.assertEquals(res, num.subtract(n1));
    }
    @Test
    public void divComplexTest(){
        Complex num = new Complex(54,27);
        Complex n1 = new Complex(11, -50);
        Complex res = new Complex(-0.28843952689813046, 1.1434566959175887);
        Assert.assertEquals(res, num.divide(n1));
    }
    @Test
    public void multiplyDoubleComplexTest(){
        Complex num = new Complex(5,27);
        Complex res = new Complex(60, 324);
        Assert.assertEquals(res, num.multiply(12));
    }
    @Test
    public void subDoubleComplexTest(){
        Complex num = new Complex(5,27);
        Complex res = new Complex(0, 27);
        Assert.assertEquals(res, num.subtract(5));
    }

    @Test
    public void parseTest(){
        String line = "23.15 - 19i";
        Complex res = Complex.parseComplex(line);
        Assert.assertTrue(res.getR() - 23.15 >= 1E-5 && res.getI() + 19 >= 1E-5);
    }
}
