package yeah.o.punch.array;

public class RemoveElement {
	
    public static int removeElement(int[] A, int elem) {
        if(A == null || A.length==0) return 0;
        
        int len = A.length;        
 
        int bound = -1;
        int ptr = 0;
        
        while(ptr < len) {
        	
        	while(ptr < len && A[ptr] == elem) {
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
		int len1 =  removeElement(a1,2);
		System.out.println(len1);
		
		int[] a2 = {1, 1, 2, 2, 2,3};
		int len2 =  removeElement(a2,2);
		System.out.println(len2);

		int[] a3 = {1, 2, 2, 2, 3, 3, 4};
		int len3 =  removeElement(a3,1);
		System.out.println(len3);
		
		int[] a4 = {1, 2};
		int len4 =  removeElement(a4,1);
		System.out.println(len4);
		
		int[] a5 = {1};
		int len5 =  removeElement(a5,1);
		System.out.println(len5);

	}

}
