package yeah.o.punch.number;

public class ReverseInteger {
	
    public static int reverse(int x) {
        long reversed = 0;
      
        while(x!=0) {
        	reversed = reversed*10 + (x%10);
        	x /= 10;
        }
        
        return (reversed==(int)reversed) ? (int)reversed : 0;
    }
    
    public static void printResult(int num) {    	
    	int res = reverse(num);
    	System.out.println(num + " is reversed into " + res);
    }
    
	public static void main(String[] args) {
		printResult(123);
		printResult(-123);
		printResult(1000000003);
	}

}
