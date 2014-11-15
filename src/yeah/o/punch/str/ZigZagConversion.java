package yeah.o.punch.str;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
	
    public static String convert(String s, int nRows) {
    	if(nRows <= 1)
    		return s;
    	
        StringBuilder converted = new StringBuilder();
        
        List<StringBuilder> res = new ArrayList<StringBuilder>();
        for(int i=1; i<=nRows; i++)
        	res.add(new StringBuilder());
        
        int col=0;
        int row=0;
        int LEN = s.length();
        for(int k=0; k<LEN; k++) {
        	char c = s.charAt(k);
    		res.get(row).append(c);
        	
        	if (col%2 == 0) {	
        		if(row == nRows-1) {
        			col++;
        			row--;
        		} else {
        			row++;
        		}
        		
        	} else {
    			row--;
        		if(row == 0)
        			col += 1;
        		else if(row < 0)
        			row=1;
        	}
        }
        
        for(int i=0; i<nRows; i++) {
        	converted.append(res.get(i).toString());
        }
        
        return converted.toString();
    }
    
    public static void printResult(String s, int n, String expected) {    	
    	String res = convert(s,n);
    	System.out.println("ZigZag converstion for " + s + " into " + n + " rows is: " + res);
    	if(res.equals(expected)) {
        	System.out.println("Correct!");
    	} else {
        	System.out.println("Wrong!");
    	}
    }

	public static void main(String[] args) {
		printResult("PAYPALISHIRING", 1, "PAYPALISHIRING");
		printResult("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
		printResult("PAYPALISHIRING", 4, "PINALSIGYAHRPI");
		printResult("ABCD", 2, "ACBD");
	}
}
