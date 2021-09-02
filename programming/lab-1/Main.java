import java.math.*;

public class Main {
    public static void main(String[] args) {
        long[] c = new long[21 - 5];
        for (int i = 0; i < 21 - 5; i++) {
            c[i] = i + 5;
        }
        double[] x = new double[12];
        for (int i = 0; i < 12; i++) {
            x[i] = -13.0 + (double) (Math.random() * 13.0);
        }
        double[][] aboba = new double[9][12];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 12; j++) {
                if (c[i] == 21) {
                    aboba[i][j] = Math.tan(Math.cbrt(Math.cos(x[j])));
                } else if (c[i] == 5 || c[i] == 7 || c[i] == 13 || c[i] == 15) {
                    aboba[i][j] = Math.cos(Math.log10(Math.sin(x[j]) * Math.sin(x[j])));
                } else {
                    aboba[i][j] = Math.pow(Math.PI / (Math.asin(Math.cos(Math.log10(Math.acos(x[j] / 26)))) + 1), 3);
                }
                System.out.print(Math.round(aboba[i][j] * 1000.0) / 1000.0);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
