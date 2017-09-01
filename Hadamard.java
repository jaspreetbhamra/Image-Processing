/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithmsGeneral;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Hadamard {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the value of n");
		int n = sc.nextInt();
		int arr[][] = new int[2*n][2*n];
		boolean negativeBlockFlag = false;
		boolean alternate = false;
//		boolean alternatingRows = false;
		if(n==1)
			System.out.println("1\t1\n1\t-1");
		else{
			for(int i=0; i<n; i+=2){
				for(int j=0; j<2*n; j+=2){
					arr[i][j] = 1;
					arr[i][j+1] = 1;
					arr[i+1][j] = 1;
					arr[i+1][j+1] = -1;
				}
			}
			for(int i=n; i<2*n; i+=2){
				for(int j=0; j<n; j+=2){
					if(!negativeBlockFlag || (negativeBlockFlag && !alternate)){
						arr[i][j] = 1;
						arr[i][j+1] = 1;
						arr[i+1][j] = 1;
						arr[i+1][j+1] = -1;
						alternate = !alternate;
					} else if((negativeBlockFlag && alternate)/* || alternatingRows*/){
						arr[i][j] = -1;
						arr[i][j+1] = -1;
						arr[i+1][j] = -1;
						arr[i+1][j+1] = 1;
						alternate = !alternate;
					}
				}
				for(int j=n; j<2*n; j+=2){
					if(!negativeBlockFlag || (negativeBlockFlag && !alternate)){
						arr[i][j] = -1;
						arr[i][j+1] = -1;
						arr[i+1][j] = -1;
						arr[i+1][j+1] = 1;
						alternate = !alternate;
					} else if((negativeBlockFlag && alternate)/* || alternatingRows*/){
						arr[i][j] = 1;
						arr[i][j+1] = 1;
						arr[i+1][j] = 1;
						arr[i+1][j+1] = -1;
						alternate = !alternate;
					}
				}
				negativeBlockFlag = !negativeBlockFlag;
				//alternatingRows = !alternatingRows;
			}
			for(int i=0; i<2*n; ++i){
				for(int j=0; j<2*n; ++j)
					System.out.print(arr[i][j] + "\t");
				System.out.println("");
			}
		}
	}
}
