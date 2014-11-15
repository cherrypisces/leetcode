package yeah.o.punch.others;

import java.util.HashMap;

public class RomToInt {
    public static int romanToInt(String s) {
    	if (s==null || s.length()==0)
    		return 0;
    	
       final HashMap<Character, Integer> roman_map = new HashMap<Character, Integer>();
       roman_map.put('I', 1);
       roman_map.put('V', 5);
       roman_map.put('X', 10);
       roman_map.put('L', 50);
       roman_map.put('C', 100);
       roman_map.put('D', 500);
       roman_map.put('M', 1000);
       
       int sum = 0;
       int len = s.length();
       char curr = s.charAt(len-1);
       sum += roman_map.get(curr);
       char prev = curr;
       boolean isPlus = true;
       for(int i=len-2; i>=0; i--) {
    	   curr = s.charAt(i);
    	   int prevVal = roman_map.get(prev);
    	   int currVal = roman_map.get(curr);
    	   if(currVal <  prevVal) {
    		   isPlus = false;
    	   } else if (currVal > prevVal){
    		   isPlus = true;
    	   }
    	   
    	   if (isPlus) {
    		   sum += roman_map.get(curr);
    	   } else {
    		   sum -= roman_map.get(curr);
    	   }
    	   prev = curr;
       }
       
       return sum;
    }
    
    public static void printResult(String s) {    	
    	int res = romanToInt(s);
    	System.out.println(s + " converted to int is: " + res);
    }
    
	public static void main(String[] args) {
		printResult("CCVII");  //207
		printResult("MLXVI");  //1066
		printResult("MCMIV");  //1904
		printResult("MMXIV");  //2014
		printResult("MCMLIV"); //1954
		printResult("MCMXC");  //1990

	}

}
