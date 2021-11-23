import java.lang.Math;

public class Main {

    public static void main(String[] args) {

        long[] c = new long[17];
        double[] x = new double[12];
        double[][] arr = new double[9][12];

        for (int i = 0, j = 5; i < c.length; j++, i++) {
            c[i] = j;
        }
        for (int i = 0; i < x.length; i++) {
            x[i] = -13.0 + (Math.random() * 13.0);
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                if (c[i] == 21) {
                    arr[i][j] = Math.tan(Math.cbrt(Math.cos(x[j])));
                } else if (c[i] == 5 || c[i] == 7 || c[i] == 13 || c[i] == 15) {
                    arr[i][j] = Math.cos(Math.log10(Math.sin(x[j]) * Math.sin(x[j])));
                } else {
                    arr[i][j] = Math.pow(Math.PI / (Math.asin(Math.cos(Math.log10(Math.acos(x[j] / 26)))) + 1), 3);
                }

                System.out.printf("%-7.3f ", arr[i][j]);
            }
            System.out.println();
        }
    }
}
