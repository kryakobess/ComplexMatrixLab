import java.util.Scanner;

public class Main {

    public static boolean fillMatrix(Complex[][] matrix, int rows, int cols, Scanner in){
        for (int i = 0; i < rows; ++i){
            for (int j = 0; j < cols; ++j){
                String num = in.next();
                try {
                    matrix[i][j] = Complex.parseComplex(num);
                }catch (RuntimeException e){
                    System.out.println("Wrong format of number! Please write it properly from the beginning!\n");
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] argv){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter rows and columns count respectively: ");
        int rows = in.nextInt();
        int cols = in.nextInt();
        Complex[][] complexes = new Complex[rows][cols];
        System.out.println("Enter numbers of matrix (If number is Complex it should be in \"a+bi\" format): ");
        while (!fillMatrix(complexes, rows, cols, in));
        ComplexMatrix complexMatrix = new ComplexMatrix(rows, cols, complexes);
        System.out.println(complexMatrix);
    }
}
