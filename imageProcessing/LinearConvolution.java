package imageProcessing;

import java.util.Scanner;

public class LinearConvolution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the first signal");
        int h = sc.nextInt();
        int[] hx = new int[h];
        System.out.println("Enter the first signal");
        for(int i=0; i<h; ++i)
            hx[i] = sc.nextInt();
        System.out.println("Enter the length of the second signal");
        int f = sc.nextInt();
        int[] fx = new int[f];
        System.out.println("Enter the second signal");
        for(int i=0; i<f; ++i)
            fx[i] = sc.nextInt();
        int result[] = performLinearConvolution(fx, hx);
        System.out.println("Output signal:");
        for (int i = 0; i < result.length; i++)
            System.out.print(result[i]+ " ");
        System.out.println();
    }

    public static int[] performLinearConvolution(int f[], int h[]){
        int convolutionMatrix[][] = new int[h.length][f.length];
        for(int i=0; i<h.length; ++i)
            for(int j=0; j<f.length; ++j)
                convolutionMatrix[i][j] = h[i] * f[j];
        int resultArr[] = new int[f.length + h.length - 1];
        int positionOfResultArr = 0;
        for(int i=0; i<f.length; ++i){
            for(int j=i; j>=0; --j)
                resultArr[positionOfResultArr] += convolutionMatrix[i-j][j];
            positionOfResultArr++;
        }
        for(int i=1; i<h.length; ++i){                      //Number of slopes
//            for(int j=f.length-1; j>=h.length-1-i; --j)
//                resultArr[positionOfResultArr] += convolutionMatrix[h.length-j-1][j];
            int k = f.length - 1;
            for(int j=i; j<h.length; ++j)
                resultArr[positionOfResultArr] += convolutionMatrix[j][k--];
            positionOfResultArr++;
        }
        return resultArr;
    }
}