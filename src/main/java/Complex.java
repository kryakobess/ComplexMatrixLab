import java.util.List;
import java.util.Objects;

public class Complex extends Number{
    private double r;
    private double i;

    public Complex(){
        this(0,0);
    }

    public Complex(double real, double imaginary){
        this.r = real;
        this.i = imaginary;
    }
    public Complex(double num){
        this(num, 0);
    }

    public static <T extends Number> Complex[] toComplexArray(T[] arr){
        Complex[] result = new Complex[arr.length];
        for (int i = 0; i < arr.length; ++i){
            result[i] = new Complex(arr[i].doubleValue());
        }
        return result;
    }

    public double getI(){
        return i;
    }

    public double getR() {
        return r;
    }

    public void setI(double i) {
        this.i = i;
    }

    public void setR(double r) {
        this.r = r;
    }

    public double getAbs(){
        return Math.sqrt(r*r + i*i);
    }

    public double getPhase(){
        return Math.atan2(i,r);
    }

    public Complex add(double num){
        return new Complex(this.r + num, this.i);
    }

    public Complex subtract(double num){
        return new Complex(this.r - num, this.i);
    }

    public Complex multiply(double num){
        return this.multiply(new Complex(num));
    }

    public Complex divide(double num){
        return this.divide(new Complex(num));
    }

    public Complex add(Complex num){
        return new Complex(this.r + num.r, this.i + num.i);
    }

    public Complex subtract(Complex num){
        return new Complex(this.r - num.r, this.i - num.i);
    }

    public Complex multiply(Complex num){
        return new Complex(this.r * num.r - this.i * num.i,
                this.r * num.i + this.i * num.r);
    }

    public Complex divide(Complex num){
        if ((num.r *num.r + num.i *num.i) != 0)
            return new Complex((this.r *num.r + this.i *num.i)/(num.r *num.r + num.i *num.i),
                    (this.i*num.r-this.r*num.i)/(num.r*num.r + num.i*num.i));
        else return null;
    }

    public String asTrigonometricForm(){
        double arg = getPhase();
        return "z = " + getAbs() + "*(cos(" + arg + ") + i*sin(" + arg + "))";
    }

    public String asExpForm(){
        return "z = " + getAbs() + "*e^(i*" + getPhase() + ")";
    }

    @Override
    public String toString() {
        return "Complex{" +
                "r=" + r +
                ", i=" + i +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complex)) return false;
        Complex complex = (Complex) o;
        return Double.compare(complex.r, r) == 0 && Double.compare(complex.i, i) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, i);
    }

    @Override
    public int intValue() {
        return (int)r;
    }

    @Override
    public long longValue() {
        return (int)r;
    }

    @Override
    public float floatValue() {
        return (float) r;
    }

    @Override
    public double doubleValue() {
        return r;
    }

}
