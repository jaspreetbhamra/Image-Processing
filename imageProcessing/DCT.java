package imageProcessing;

import java.util.Scanner;
import static java.lang.System.*;
import java.text.DecimalFormat;

public class DCT{

	static int n;

   public static void main(String args[]){
   	Scanner sc = new Scanner(System.in);
   	out.println("Enter the value of N");
   	n = sc.nextInt();
      double imageMatrix[][] = new double[n][n];
      out.println("Enter the image matrix");
      
      for(int i=0; i<n; ++i)
         for(int j=0; j<n; ++j)
            imageMatrix[i][j] = sc.nextInt();
            
      double DCTMatrix[][] = new double[n][n];
     	for(int u=0; u<n; ++u){
     		DCTMatrix[u] = computeF(u);
     	}
     	
     	double result[][] = multiply(DCTMatrix, imageMatrix);
     	result = multiply(result, transpose(DCTMatrix));
     	
     	DecimalFormat df = new DecimalFormat("#0.0000");
     	
     	for(int i=0; i<n; ++i){
         for(int j=0; j<n; ++j)
            out.print(df.format(result[i][j]) + " ");
         out.println();
      }
      
   }
   
   public static double[][] transpose(double a[][]){
   	double transposeMatrix[][] = new double[n][n];
   	for(int i=0; i<n; ++i)
   		for(int j=0; j<n; ++j)
   			transposeMatrix[i][j] = a[j][i];
   	return transposeMatrix;
   }
   
   public static double[][] multiply(double a[][], double b[][]){
   	double c[][] = new double[n][n];
   	for(int i = 0; i < n; i++){
   		for(int j = 0; j < n; j++){
         	for(int k = 0; k < n; k++){
             	c[i][j] = c[i][j] + a[i][k] * b[k][j];
            }
         }
      }
      return c;
   }
   
   public static double[] computeF(int u){
   	double alpha = 0;
   	if(u == 0)
   		alpha = ((double)1)/((double)Math.sqrt(n));
   	else
   		alpha = ((double)Math.sqrt(2))/((double)Math.sqrt(n));
   	double arr[] = new double[n];					//1 row of DCT
   	for(int x=0; x<n; ++x){
   		double cosValue = Math.cos((2*x + 1)*u*Math.PI/(2*n));
   		arr[x] = alpha * cosValue;
   	}
   	return arr;
   }
}

/*
136 142 148 161
138 145 149 164
131 143 150 158
135 139 149 162
*/
