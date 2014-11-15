package yeah.o.punch.array;

import yeah.o.punch.Utilities;

public class MergeSortedArray {
	
    public static void merge(int A[], int m, int B[], int n) {
        for(int i=m+n-1; i>=n; i--) {
        	A[i] = A[i-B.length];
        }
    	 
        int i=n, j=0;
        for(int k=0; k<m+n; k++) {
        	 if (i<m+n && j<n) {
        		 if (A[i] < B[j])
            		 A[k] = A[i++];
            	 else 
            		 A[k] = B[j++];
        	 } else if (i<m+n) {
        		 A[k] = A[i++];
        	 } else if (j<n) {
        		 A[k] = B[j++];
        	 }
        }
    }
	
	public static void main(String[] args) {
		int[] a1 = {2,5,7,8, -1,-1,-1};
		int[] b1 = {1};
		merge(a1,4,b1,1);
		Utilities.printArray(a1);		

		int[] a2 = {-1,-1,-1,-1,-1,-1,-1};
		int[] b2 = {2,5,7,8};
		merge(a2,0,b2,4);
		Utilities.printArray(a2);
	}
}
