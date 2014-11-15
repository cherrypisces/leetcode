package yeah.o.punch.others;

import yeah.o.punch.Utilities;

/*
 * http://oj.leetcode.com/problems/gas-station/
 * 
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * 
 * Note:
 * The solution is guaranteed to be unique.
 * 
 */

public class GasStation {
	
	/*
	 *  0 -- 1 
	 *  |    |
	 *  4    2     =>   0 - 1 - 2 - 3- 4 - 0(5) - 1(6) - 2(7) - 3(8) - 4(9)
	 *   \  /
	 *     3 
	 */
	
	
	/* EXCEED TIME LIMIT  */	
    public static int canCompleteCircuitSlow(int[] gas, int[] cost) {        
    	int len = gas.length;
    	for(int start=0; start<len; start++) {
        	int leftSum = 0;
        	int i;
    		for(i=start; i<start+len; i++) {
    			int pos = i%len;
    			leftSum += gas[pos] - cost[pos];
    			if(leftSum < 0 )
    				break;
    		}
    		if(i==start+len)
    			return start;
    	}
    	return -1;
    }
    
    
    public static int canCompleteCircuit(int[] gas, int[] cost) {        
    	int len = gas.length;
    	int leftSum = 0, start = 0;
    	for(int i=0; i<2*len; i++) {
    		int pos = i%len;
    		leftSum += gas[pos] - cost[pos];
    		if(leftSum < 0 ) {
    			start = i+1;
    			leftSum = 0;
    		}
    	}
    	
    	if(start<len)
    		return start;
    	
    	return -1;
    }
		
    public static void printSolution(int[] gas, int[] cost) {
    	
    	for(int i=0; i<gas.length; i++) {
    		System.out.print("["+i+"]:("+gas[i]+") --" + cost[i] + "-- ");
    	}
    	System.out.println("(End)");
    	
    	int start = canCompleteCircuit(gas,cost);
    	String answer = start==-1 ? "CAN NOT COMPLETE!" : "CAN START FROM " + start;
    	System.out.println(answer);    	

    	//int start2 = canCompleteCircuitSlow(gas,cost);
    	//String answer2 = start2==-1 ? "CAN NOT COMPLETE!" : "CAN START FROM " + start2;
    	//System.out.println(answer);    	
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = Utilities.randomIntInRange(5, 10);
		int[] gas = Utilities.randomArray(n, 5, 10);
		int[] cost = Utilities.randomArray(n, 5, 10);
		
		printSolution(gas, cost);
		
	}

}
