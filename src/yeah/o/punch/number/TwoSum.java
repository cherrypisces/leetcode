package yeah.o.punch.number;

import java.util.HashMap;

public class TwoSum {	
	/** 
	 * TLE 
	 */
    public static int[] twoSum_Slow(int[] numbers, int target) {    
    	for(int i=0; i<numbers.length-1; i++) {
    		int another = target - numbers[i];
    		for(int j=i+1; j<numbers.length; j++) {
    			if (another == numbers[j]) {
    				return new int[]{i+1, j+1};
    			}
    		}
    	}   	
    	
    	return new int[]{};
    }
    
    
    public static int[] twoSum(int[] numbers, int target) {    
    	HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
    	for(int i=0; i<numbers.length; i++) {
    		map.put(numbers[i], i+1);
    	}
    	
    	for(int i=0; i<numbers.length; i++) {
    		int another = target - numbers[i];
    		if (map.containsKey(another) && map.get(another)!=i+1) {
    			return new int[] {i+1, map.get(another)};
    		}
    	}
    	
    	return new int[]{};
    }
    
    public static void printAnswer(int[] numbers, int target) {
    	int[] res = twoSum(numbers, target);
		System.out.println(res[0]+ ", " + res[1]);	
    }
    
	public static void main(String[] args) {
		int[] numbers1 = {2, 7, 11, 15};
		int target1=9;
		printAnswer(numbers1, target1);
		
		int[] numbers2 = {3, 2, 4};
		int target2=6;
		printAnswer(numbers2, target2);
		
		int[] numbers3 = {5, 5, 14};
		int target3=10;
		printAnswer(numbers3, target3);
	}
}
