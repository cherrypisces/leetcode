package yeah.o.punch.str;

public class AddBinary {
	
    public static String addBinary(String a, String b) {
        if (a==null)
        	return b;
        else if (b==null)
        	return a;
    	
    	int len_a = a.length();
    	int len_b = b.length();    	
    	String sum = new String();
    	int i=len_a-1, j=len_b-1;
    	byte carry = 0;
    	while(i>=0 || j>=0) {
    		byte digit_a = i<0 ? (byte)0 : (a.charAt(i) == '1' ? (byte)1 : (byte)0);
    		byte digit_b = j<0 ? (byte)0 : (b.charAt(j) == '1' ? (byte)1 : (byte)0);
    		
    		byte s = (byte)(digit_a + digit_b + carry);
    		
    		if (s>=2) {
    			carry = 1;
    			s -= (byte)2;
    		} else {
    			carry = 0;
    		}
    		
    		sum = s + sum;
    		
    		i--;
    		j--;
    	}
    	
    	if (carry == 1)
    		sum = carry + sum; 
    	
    	return sum.toString();
    }

    public static void printResult(String a, String b) {
    	String res = addBinary(a, b);
    	System.out.println(a + " + " + b + "=" + res);
    }
    
	public static void main(String[] args) {
		printResult("11", "1");
		printResult("1010", "1011");
	}

}
