## Текст задания
1) Создать одномерный массив c типа long. Заполнить его нечётными числами от 5 до 21 включительно в порядке возрастания.
2) Создать одномерный массив x типа double. Заполнить его 12-ю случайными числами в диапазоне от -13.0 до 13.0.
3) Создать двумерный массив c размером 9x12. Вычислить его элементы по следующей формуле (где x = x[j]):
    * если c[i] = 21, то ![](https://latex.codecogs.com/gif.latex?%5Cdpi%7B150%7D%26space%3B%5Cbg_black%26space%3B%5Ctiny%26space%3B%7B%5Ccolor%7Bwhite%7D%26space%3Bc%5Bi%5D%5Bj%5D%26space%3B%3D%26space%3B%5Ctan%28%5Csqrt%5B3%5D%7B%5Ccos%28x%29%7D%29%7D)
    * если c[i] ∈ {5, 7, 13, 15}, то ![](https://latex.codecogs.com/gif.latex?%5Cdpi%7B150%7D%26space%3B%5Cbg_black%26space%3B%5Ctiny%26space%3B%7B%5Ccolor%7Bwhite%7D%26space%3Bc%5Bi%5D%5Bj%5D%26space%3B%3D%26space%3B%5Ccos%28%5Cln%28sin%28x%29%5E%7B2%7D%29%29%7D)
    * для остальных значений c[i]: ![](https://latex.codecogs.com/gif.latex?%5Cdpi%7B110%7D%26space%3B%5Cbg_black%26space%3B%5Csmall%26space%3B%7B%5Ccolor%7Bwhite%7D%26space%3Bc%5Bi%5D%5Bj%5D%26space%3B%3D%26space%3B%5Cleft%28%5Cfrac%7B%5Cpi%7D%7B%5Carcsin%28%5Ccos%28%5Cln%28%5Carccos%28%5Cfrac%7Bx%7D%7B26%7D%29%29%29%29%2B1%7D%5Cright%29%5E%7B3%7D%7D)
    * Напечатать полученный в результате массив в формате с тремя знаками после запятой.
## Исходный код программы
```
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

```
## Результат работы программы
```
0.992 0.523 0.999 1.000 0.280 0.780 0.997 0.985 0.993 0.697 0.762 0.906 
2.674 2.597 2.549 2.367 2.405 2.427 2.448 2.383 2.348 2.496 2.427 2.587 
0.992 0.523 0.999 1.000 0.280 0.780 0.997 0.985 0.993 0.697 0.762 0.906 
2.674 2.597 2.549 2.367 2.405 2.427 2.448 2.383 2.348 2.496 2.427 2.587 
2.674 2.597 2.549 2.367 2.405 2.427 2.448 2.383 2.348 2.496 2.427 2.587 
2.674 2.597 2.549 2.367 2.405 2.427 2.448 2.383 2.348 2.496 2.427 2.587 
2.674 2.597 2.549 2.367 2.405 2.427 2.448 2.383 2.348 2.496 2.427 2.587 
2.674 2.597 2.549 2.367 2.405 2.427 2.448 2.383 2.348 2.496 2.427 2.587 
0.992 0.523 0.999 1.000 0.280 0.780 0.997 0.985 0.993 0.697 0.762 0.906 
```
> Следует учитывать, что каждый новый запуск программы будет давать новый уникальный вывод.

## Выводы по работе
???
