package yeah.o.punch.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
	
    public static List<List<Integer>> threeSum_TLE(int[] num) {
    	List<List<Integer>> all = new ArrayList<List<Integer>>();
    	
    	Arrays.sort(num);
    	
    	for(int i=0; i<num.length-2; i++) {
    		for(int j=i+1; j<num.length-1; j++) {
    			for(int k=j+1; k<num.length; k++) {
    				
    				if (num[i] + num[j] + num[k] == 0) {
    					List<Integer>  res = new ArrayList<Integer>();    					
    					res.add(num[i]);
    					res.add(num[j]);
    					res.add(num[k]);
				
    					if(!all.contains(res))
    						all.add(res);
    				}
    			}
    		}    		
    	}
    	
    	return all;
    }  
    
    public static List<List<Integer>> threeSum(int[] num) {
    	List<List<Integer>> all = new ArrayList<List<Integer>>();
    	
    	Arrays.sort(num);
    	
    	
  
    	return all;
    }
    
    public static void printResult(int[] num) {
    	List<List<Integer>> res = threeSum(num);
    	
    	for(List<Integer> triple : res) {
    		System.out.print("( ");
    		for(int i : triple) {
    			System.out.print(i + " ");
    		}
    		System.out.println(")");
    	}
    }
    
	public static void main(String[] args) {
		int[] num1 = {-1,0,1,2,-1,-4};
		printResult(num1);
	}

}
