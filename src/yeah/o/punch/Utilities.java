package yeah.o.punch;

import java.util.Random;

public class Utilities {

	public static double randomDoubleInRange(double min, double max) {
		Random r = new Random();
		double d = (max-min)*r.nextDouble();
		return (d+min);
	}
	// min<= <=max
	public static int randomIntInRange(int min, int max){
		Random r = new Random();
		int n = r.nextInt(max-min+1);
		return (n+min);
	}
	
	// only for characters [0-9]
	public static char randomCharInRange(int min, int max){
		Random r = new Random();
		int n = r.nextInt(max-min+1);
		return Integer.toString(n+min).charAt(0);
	}
	
	public static int[][] randomMatrix(int M, int N, int min, int max){
		int[][] matrix = new int[M][N];
		for(int i=0;i<M;i++)
			for(int j=0;j<N;j++){
				matrix[i][j] = randomIntInRange(min, max);
			}
		return matrix;
	}
	
	public static int[] randomArray(int N, int min, int max){
		int[] array = new int[N];
		for(int j=0;j<N;j++){
			array[j] = randomIntInRange(min, max);
		}
		return array;
	}
	
	public static void printMatrix(int[][] matrix) {
		for(int i=0; i<matrix.length; i++) {
			for(int j=0; j<matrix[i].length; j++) {
				if(matrix[i][j] >= 0) {
					System.out.print(" ");
				}
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void printArray(int[] array) {
		for(int i=0; i<array.length; i++) {
				System.out.print(" " + array[i]);
			}
		System.out.println();
	}
	
	
	
}
