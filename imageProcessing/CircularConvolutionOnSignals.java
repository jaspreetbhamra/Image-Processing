package imageProcessing;

import java.util.Scanner;

public class CircularConvolutionOnSignals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the lengths of the two signals");
        int f = sc.nextInt();
        int []f1 = new int[f];
        System.out.println("Enter the first signal");
        for(int i = 0; i < f; i++)                     // 0 1 2 3 => clockwise
            f1[i] = sc.nextInt();
        int []f2 = new int[f];
        System.out.println("Enter the second signal");
        f2[0] = sc.nextInt();
        for(int i = f-1; i >= 1; --i)
            f2[i] = sc.nextInt();
        int resultArr[] = performCircularConvolution(f1, f2);
        System.out.println("The result is:");
        for(int i=0; i<resultArr.length; ++i)
            System.out.print(resultArr[i] + " ");
        System.out.println();
    }

    public static int[] performCircularConvolution(int[] f1, int[] f2){
        int resultArr[] = new int[f1.length];
        for(int i=0; i<f1.length; ++i){
            for(int j=0; j<f1.length; ++j){
                resultArr[i] += f1[j] * f2[j];
            }
            int temp = f2[f2.length-1];
            for(int j=f2.length-1; j>0; --j) {
                //rotate f2
                f2[j] = f2[j - 1];
            }
            f2[0] = temp;
        }
        return resultArr;
    }
}
