package yeah.o.punch.array;

public class RemoveDuplicateFromSortedArray {

	public static int removeDuplicates(int[] A) {
        if (A == null) return 0;
        
        int len = A.length;
        if (len <= 1) return len;
        
        int bound = 0;
        int ptr = 1;
		
        while(ptr < len) {
        	while(ptr<len && A[ptr] == A[bound]) {
        		ptr++;
        	}
        	
        	if (ptr < len) {
        		A[++bound] = A[ptr++];
        	}        	
        }
        
        return bound+1;
    }
	
	public static void main(String[] args) {
		int[] a1 = {1, 1, 2};
		int len1 =  removeDuplicates(a1);
		System.out.println(len1);
		
		int[] a2 = {1, 1, 2, 2, 2,3};
		int len2 =  removeDuplicates(a2);
		System.out.println(len2);

		int[] a3 = {1, 2, 2, 2, 3, 3, 4};
		int len3 =  removeDuplicates(a3);
		System.out.println(len3);
		
		int[] a4 = {1, 2};
		int len4 =  removeDuplicates(a4);
		System.out.println(len4);
		
		int[] a5 = {1, 1};
		int len5 =  removeDuplicates(a5);
		System.out.println(len5);
	}

}
