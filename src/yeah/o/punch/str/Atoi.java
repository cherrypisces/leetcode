package yeah.o.punch.str;

public class Atoi {
	
	 public static int atoi(String str) {
	     if(str==null || str.length()==0)
	    	 return 0;
	   
	     int start = 0;
	     while(str.charAt(start)==' ' || str.charAt(start)=='\t')
	    	 start++;	     

	     boolean isNegative = false;
	     if (str.charAt(start)=='+' || str.charAt(start)=='-') {
		     isNegative = str.charAt(start)=='-' ? true:false;
	    	 start++;
	     }
	     
	     int num = 0;
	     for(int i=start; i<str.length(); i++) {
	    	 char c = str.charAt(i);
	    	 if(c<48 || c>57)    // '0'-48, '5'-57
	    		 break;
	    	 
	    	 if((num==214748364 && c>'7') || num>214748364) {
	    		 return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
	    	 }
	    	 num = 10*num + (c-'0');
	     }

	     return isNegative ? (-1)*num : num;
	 }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String n1 = "  - 321";
		int res = atoi(n1);
		System.out.println("convert "+n1+" to " + res);
		
		String n2 = "2147483648";
		res = atoi(n2);
		System.out.println("convert "+n2+" to " + res);
		
		String n3 = "      -11919730356x";
		res = atoi(n3);
		System.out.println("convert "+n3+" to " + res);
		
	}

}
