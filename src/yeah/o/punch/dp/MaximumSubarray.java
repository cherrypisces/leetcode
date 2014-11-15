package yeah.o.punch.dp;

import yeah.o.punch.Utilities;

/*
 * http://oj.leetcode.com/problems/maximum-subarray/
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * 
 * For example, given the array [−2,1,−3,4,−1,2,1,−5,4], 
 * 		the contiguous subarray [4,−1,2,1] has the largest sum = 6.
 * 
 */

public class MaximumSubarray {

	public static int maxSumSubarray(int[] array) {
		
		int max = array[0];
		int sum = 0;
		for(int i=0; i<array.length; i++) {
			if(sum < 0)
				sum = array[i];
			else
				sum += array[i];
			
			if(sum > max)
				max = sum;
		}
		return max;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = Utilities.randomArray(10, -10, 10);
		System.out.println("The array is:" );
		Utilities.printArray(array);
		System.out.println("Sum is: " + maxSumSubarray(array));
	}

}
