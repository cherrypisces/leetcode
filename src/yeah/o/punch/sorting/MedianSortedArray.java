package yeah.o.punch.sorting;

import yeah.o.punch.Utilities;

/*
 * 
 * http://oj.leetcode.com/problems/median-of-two-sorted-arrays/
 * 
 * There are two sorted arrays A and B of size m and n respectively. 
 * Find the median of the two sorted arrays. 
 * The overall run time complexity should be O(log (m+n)).
 * 
 */

public class MedianSortedArray {

    public static double findMedianSortedArrays_notsure(int A[], int B[]) {
 
    	int a_left = 0, a_right = A.length-1;
    	int b_left = 0, b_right = B.length-1;
    	
    	while(true) {     		
    		int a_mid = (a_left + a_right) / 2;
    		double a_median = A[a_mid];
    		if ( (a_right-a_left+1)%2==0 ) {
    			a_median = 1.0 * (A[a_mid] + A[a_mid+1]) / 2;
    		} 

       		int b_mid = (b_left + b_right) / 2;
    		double b_median = B[b_mid];
    		if ( (b_right-b_left+1)%2==0 ) {
    			b_median = 1.0 * (B[b_mid] + B[b_mid+1]) / 2;
    		} 
    		
    		if (a_median == b_median){
    			return a_median;
    		} else if (a_median < b_median) {
    			a_left = a_mid+1;
    			b_right = b_mid-1;    			
    			if (a_left > a_right) {
    				return a_median;
    			}
    		} else {
    			b_left = b_mid+1;
    			a_right = a_mid-1;    			
    			if(b_left > b_right) {
    				return b_median;
    			}
    		}
    	} 
    	
    }
    
    public static double findMedianSortedArrays(int A[], int B[]) {
    	double median = 0.0;
    	
    	
    	
    	return median;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = Utilities.randomArray(11, 1, 100);
		SortAlgos.quickSort(A, 0, A.length-1);
		System.out.println("A is: ["+ A.length + "]" );
		Utilities.printArray(A);
		int[] B = Utilities.randomArray(20, 1, 100);
		SortAlgos.quickSort(B, 0, B.length-1);
		System.out.println("B is: ["+ B.length + "]" );
		Utilities.printArray(B);
			
		/////////////////////////////////////////////
		int[] C= new int[A.length + B.length];
		System.arraycopy(A, 0, C, 0, A.length);
		System.arraycopy(B, 0, C, A.length, B.length);
		SortAlgos.quickSort(C, 0, C.length-1);
		System.out.println("\nAfter merge:" );
		Utilities.printArray(C);
		double medianExpect = (C.length%2==1) ? C[(C.length-1)/2]  
							  : (1.0 * (C[(C.length-1)/2]+C[(C.length-1)/2 + 1]) / 2) ;
		System.out.println("Expected median is: " + medianExpect );
		/////////////////////////////////////////////
		
		double median = findMedianSortedArrays(A, B);
		System.out.println("\nCalculated Median is:" + median);
	}

}
