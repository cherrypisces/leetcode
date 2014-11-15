package yeah.o.punch.others;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {
	
	public static List<Integer> getRow(int rowIndex) {
		List<Integer> row = new ArrayList<Integer>();
		
		row.add(1);	
		for(int r=1; r<=rowIndex; r++) {
			int left 	= row.get(0);
			for(int i=1; i<r; i++) {
				int right = row.get(i);
				row.set(i, left+right);
				left = right;
			}
			row.add(1);
		}
		
		return row;
	}
	
	/** Use O(K^2) space */
    public static List<Integer> getRow_WastingSpace(int rowIndex) {
        return generate(rowIndex+1).get(rowIndex);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        if (numRows == 0) 
        	return triangle;
        
        List<Integer> currRow = new ArrayList<Integer>();
        currRow.add(1);
        triangle.add(currRow);

        List<Integer>prevRow = currRow;
        int prevLength = prevRow.size();  
        for(int r=2; r<=numRows; r++) {    	   
        	currRow = new ArrayList<Integer>();
        	currRow.add(1);
        	for(int t=0; t<prevLength-1; t++) {
        		int ele = prevRow.get(t) + prevRow.get(t+1);
        		currRow.add(ele);
        	}
        	currRow.add(1);
        	triangle.add(currRow);

        	prevRow = currRow;
        	prevLength = prevRow.size(); 
        }
        
        return triangle;
    }
    
    public static void printPascalTriangle(List<List<Integer>> triangle) {   	
    	System.out.println("[");    	
    	for(List<Integer> row : triangle) {
    		System.out.print("	[");
    		Object[] numbers = row.toArray();
    		for(int i=0; i<numbers.length; i++) {
    			System.out.print(numbers[i]);
    			if (i<numbers.length-1)
    				System.out.print(", ");
    		}    		
    		System.out.println("]");
    	}    	
    	System.out.println("]");
    }
    
    public static void printRow(List<Integer> row) {
		System.out.print("	[");
		Object[] numbers = row.toArray();
		for(int i=0; i<numbers.length; i++) {
			System.out.print(numbers[i]);
			if (i<numbers.length-1)
				System.out.print(", ");
		}    			
		System.out.println("]");
    }
	
	public static void main(String[] args) {
		for(int n=0; n<=7; n++) {
			List<List<Integer>> triangle = generate(n);
			printPascalTriangle(triangle);
			System.out.println("----------------------------------------");
		}
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<Integer> row = getRow(34);
		printRow(row);
	}
}
