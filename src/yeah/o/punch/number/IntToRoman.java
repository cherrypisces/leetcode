package yeah.o.punch.number;

import java.util.HashMap;

public class IntToRoman {
	/**
	 * http://literacy.kent.edu/Minigrants/Cinci/romanchart.htm
	 * 
	 * 4 & 9 should be treated specially
	 * 
	 * @param num
	 * @return
	 */
    public static String intToRoman(int num) {
       
        final HashMap<Integer, Character> roman_map = new HashMap<Integer, Character>();
        roman_map.put(1,  'I');
        roman_map.put(5,  'V');
        roman_map.put(10, 'X');
        roman_map.put(50, 'L');
        roman_map.put(100,'C');
        roman_map.put(500, 'D');
        roman_map.put(1000,'M');
        
        StringBuilder res = new StringBuilder();
        
        int divisor = 1000;
        while(divisor > 0) {
        	int digit = num / divisor;
        	
        	if (digit == 9) {
    			res.append(roman_map.get(divisor));
        		res.append(roman_map.get(divisor*10));
    			
    			digit-= 9;
    			
        	} else if (digit >=5) {
        		if (divisor != 1000)
        			res.append(roman_map.get(5*divisor));
        		else
        			res.append(roman_map.get(divisor));
        		
        		digit -= 5;
        	} 
        	
        	if (digit == 4) {
        		res.append(roman_map.get(divisor));
        		res.append(roman_map.get(5*divisor)); 
        	} else if (digit > 0){
        		while(digit>0) {
        			res.append(roman_map.get(divisor));
        			digit--;
        		}
        	}

        	num %= divisor;
        	divisor /= 10;
        }
        
        return res.toString();
    }
    
    
    public static void printResult(int num) {
    	String res = intToRoman(num);
    	System.out.println(num + " converted to Roman Numeral is: " + res);
    }
    
	public static void main(String[] args) {
		printResult(3999);
		printResult(1);
		printResult(4);
		printResult(9);
		printResult(14);
		printResult(19);
		printResult(24);
		printResult(29);
		printResult(34);
		printResult(39);
		printResult(44);
		printResult(49);
		printResult(99);
	}
}
