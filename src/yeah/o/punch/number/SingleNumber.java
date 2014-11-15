package yeah.o.punch.number;

public class SingleNumber {	
	/**
	 * Given an array of integers, every element appears two times except for one. Find that single one
	 * 
	 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 * 
	 */
	public static int singleNumber(int[] A) {
		int sum = 0;
		for(int i : A) {
			sum ^= i;
		}		
		return sum;
	}
	
	/**
	 * Given an array of integers, every element appears three times except for one. Find that single one
	 * 
	 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 * 
	 */	
	public static int singleNumberII(int[] A) {
		int result = 0;
		
		for(int i=0; i<32; i++) {
			int count = 0;
			for(int num : A) {
				count += (num >> i) & 1;
			}
			result |= (count%3) << i;			
		}
		
		return result;
	}
	
	
	/**
	 * Hints: https://oj.leetcode.com/discuss/857/constant-space-solution
	 * 
	 */
	public static int singleNumberII2(int[] A) {
		int once = 0, twice = 0, thrice = 0;
		
		for(int num : A) {
			twice |= once & num;
			once ^= num;
			thrice = once & twice;  // '1' in twice not introduced by this num & '1' in once introduced by this num
			once  &= ~thrice;
			twice &= ~thrice;
		}
		
		return once;
	}
	
	
	public static int twiceNumber(int[] A) {
		int once = 0, twice = 0, thrice = 0;
		
		for(int num : A) {
			twice |= once & num;
			once ^= num;
			thrice = once & twice;  // '1' in twice not introduced by this num & '1' in once introduced by this num
			once  &= ~thrice;
			twice &= ~thrice;
		}
		
		return twice;
	}
	
	public static void main(String[] args) {
		int[] A1 = new int[] {1,2,3,3,2,1,11};		
		System.out.println("Case 1: "+singleNumber(A1));
		
		int[] A2 = new int[] {};		
		System.out.println("Case 2: "+singleNumber(A2));
		
		int[] A11 = new int[] {1,2,2,3,3,3,2,1,1,7};		
		System.out.println("Case 1_1: "+singleNumberII(A11));	
		System.out.println("Case 1_2: "+singleNumberII2(A11));
		
		int[] A22 = new int[] {2,2,3,2};		
		System.out.println("Case 2_1: "+singleNumberII(A22));	
		System.out.println("Case 2_2: "+singleNumberII2(A22));
		
		int[] A3 = new int[] {2,2,3,3,2};		
		System.out.println("Case 3: "+twiceNumber(A3));
	}

}
