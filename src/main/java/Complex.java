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
    /*
    * This function converts array of Number objects into
    * array of Complex objects
    */
    public static <T extends Number> Complex[] toComplexArray(T[] arr){
        if (arr instanceof Complex[]){
            return (Complex[]) arr;
        } else {
            Complex[] result = new Complex[arr.length];
            for (int i = 0; i < arr.length; ++i) {
                result[i] = new Complex(arr[i].doubleValue());
            }
            return result;
        }
    }
    /*
    * Returns imaginary part of Complex number
    */
    public double getI(){
        return i;
    }
    /*
     * Returns real part of Complex number
     */
    public double getR() {
        return r;
    }
    /*
     * Sets imaginary part of Complex number
     */
    public void setI(double i) {
        this.i = i;
    }
    /*
     * Sets real part of Complex number
     */
    public void setR(double r) {
        this.r = r;
    }
    /*
     * Returns absolute value of Complex number
     */
    public double getAbs(){
        return Math.sqrt(r*r + i*i);
    }
    /*
     * Returns phase of Complex number
     */
    public double getPhase(){
        return Math.atan2(i,r);
    }
    /*
     * Returns Complex object as a result of addition between this complex value and some double number
     */
    public Complex add(double num){
        return new Complex(this.r + num, this.i);
    }
    /*
     * Returns Complex object as a result of subtraction between this complex value and some double number
     */
    public Complex subtract(double num){
        return new Complex(this.r - num, this.i);
    }
    /*
     * Returns Complex object as a result of multiplication between this complex value and some double number
     */
    public Complex multiply(double num){
        return this.multiply(new Complex(num));
    }
    /*
     * Returns Complex object as a result of division between this complex value and some double number
     */
    public Complex divide(double num){
        return this.divide(new Complex(num));
    }
    /*
     * Returns Complex object as a result of addition between this complex value and the other Complex number
     */
    public Complex add(Complex num){
        return new Complex(this.r + num.r, this.i + num.i);
    }
    /*
     * Returns Complex object as a result of subtraction between this complex value and the other Complex number
     */
    public Complex subtract(Complex num){
        return new Complex(this.r - num.r, this.i - num.i);
    }
    /*
     * Returns Complex object as a result of multiplication between this complex value and the other Complex number
     */
    public Complex multiply(Complex num){
        return new Complex(this.r * num.r - this.i * num.i,
                this.r * num.i + this.i * num.r);
    }
    /*
     * Returns Complex object as a result of division between this complex value and the other Complex number.
     * Throws a RuntimeException
     */
    public Complex divide(Complex num){
        if ((num.r *num.r + num.i *num.i) != 0)
            return new Complex((this.r *num.r + this.i *num.i)/(num.r *num.r + num.i *num.i),
                    (this.i*num.r-this.r*num.i)/(num.r*num.r + num.i*num.i));
        else {
            throw new ArithmeticException("Division by zero exception!");
        }
    }
    /*
     * Returns String object of this Complex object in trigonometric form
     */
    public String asTrigonometricForm(){
        double arg = getPhase();
        return "z = " + getAbs() + "*(cos(" + arg + ") + i*sin(" + arg + "))";
    }
    /*
     * Returns String object of this Complex object in exponential form
     */
    public String asExpForm(){
        return "z = " + getAbs() + "*e^(i*" + getPhase() + ")";
    }
    /*
     * Returns a Complex object which was parsed from a line.
     * Throws a RuntimeException
     */
    public static Complex parseComplex(String line) {
        try {
            return new Complex(Double.parseDouble(line));
        } catch (NumberFormatException e) {
            line = line.replaceAll("\\s", "");
            line = line.substring(0, line.length() - 1);
            String[] values = line.split("\\+");
            if (values.length == 1) {
                values = line.split("\\-");
                values[1] = "-" + values[1];
            }
            try {
                return new Complex(Double.parseDouble(values[0]), Double.parseDouble(values[1]));
            } catch (NumberFormatException ex) {
                throw new NumberFormatException(line + " is not a complex number");
            }
        }
    }


    @Override
    public String toString() {
        return  r + " + " +"(" + i + "i)";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Complex complex)) return false;
        return Double.compare(complex.getR(), getR()) == 0 && Double.compare(complex.getI(), getI()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getR(), getI());
    }

}
