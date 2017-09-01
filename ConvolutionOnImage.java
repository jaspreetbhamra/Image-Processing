package algorithmsGeneral;

import java.util.Scanner;

public class ConvolutionOnImage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the dimensions of the image matrix");
        int m1 = sc.nextInt(), n1 = sc.nextInt();
        int f[][] = new int[m1][n1];
        System.out.println("Enter the image matrix");
        for(int i=0; i<m1; ++i)
            for(int j=0; j<n1; ++j)
                f[i][j] = sc.nextInt();
        System.out.println("Enter the dimensions of the impulse response matrix");
        int m2 = sc.nextInt(), n2 = sc.nextInt();
        int h[][] = new int[m2][n2];
        System.out.println("Enter the impulse response matrix");
        for(int i=0; i<m2; ++i)
            for(int j=0; j<n2; ++j)
                h[i][j] = sc.nextInt();
        performConvolution(m1, n1, m2, n2, f, h);
    }

    public static void performConvolution(int m1, int n1, int m2, int n2, int f[][], int h[][]){
        int m, n, size;
        int fNew[][];
        int hNew[][];
        if(m1==m2 && n1==n2) {
            m = m1;
            n = n1;
            size = m*n;
            fNew = f;
            hNew = h;
        }
        else {
            m = m1 + m2 - 1;
            n = n1 + n2 - 1;
            fNew = new int[m][n];
            int oldI = 0;
            for (int i = m - m1; i < m; ++i) {
                for (int j = 0; j < n1; ++j) {
                    fNew[i][j] = f[oldI][j];
                }
                oldI++;
            }
            oldI = 0;
            hNew = new int[m][n];
            for (int i = m - m2; i < m; ++i) {
                for (int j = 0; j < n2; ++j)
                    hNew[i][j] = h[oldI][j];
                oldI++;
            }
            size = m * n;
        }
        System.out.println("The new image matrix is:");
        print2DMatrix(m, n, fNew);
        System.out.println("The new impulse response matrix is:");
        print2DMatrix(m, n, hNew);
        int convolutionMatrix[][] = new int[size][size];
        int x = 0;
        for(int i=m-1; i>=0; --i) {
            for (int k = 0; k < n; ++k) {
                convolutionMatrix[x][0] = fNew[i][k];
                x++;
            }
        }
        for(int j=1; j<convolutionMatrix.length; ++j){
            convolutionMatrix[0][j] = convolutionMatrix[convolutionMatrix.length-1][j-1];
            for(int k=1; k<convolutionMatrix.length; ++k){
                convolutionMatrix[k][j] = convolutionMatrix[k-1][j-1];
            }
        }
        int transformedHMatrix[][] = new int[size][1];
        x = 0;
        for(int i=m-1; i>=0; --i) {
            for (int k = 0; k < n; ++k) {
                transformedHMatrix[x][0] = hNew[i][k];
                x++;
            }
        }
        System.out.println("The transformed image matrix is:");
        print2DMatrix(size, size, convolutionMatrix);
        System.out.println("The transformed image response matrix is:");
        print2DMatrix(size, 1, transformedHMatrix);
        int result[][] = multiplyMatrices(convolutionMatrix, transformedHMatrix, size);
        int resultMatrix[][] = new int[m][n];
        x = 0;
        for(int i=m-1; i>=0; --i) {
            for (int k = 0; k < n; ++k) {
                resultMatrix[i][k] = result[x][0];
                x++;
            }
        }
        System.out.println("The result is:");
        print2DMatrix(m, n, resultMatrix);
    }

    public static void print2DMatrix(int m, int n, int arr[][]){
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }
    }

    public static int[][] multiplyMatrices(int a[][], int b[][], int n){
        int c[][] = new int[n][1];
        for (int i = 0; i < n; i++)
                for (int k = 0; k < n; k++)
                    c[i][0] = c[i][0] + a[i][k] * b[k][0];
        return c;
    }

}
