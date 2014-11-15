package yeah.o.punch.number;

public class PalindromeNumber {
	
    public static boolean isPalindrome(int x) {
    	if (x<0)
    		return false;
       
    	long leftDivisor = 1;
    	while(x/leftDivisor>0){
    		leftDivisor *= 10;
    	}
    	leftDivisor /= 10;
    	
    	int rightDivisor = 10;  	
    	while(leftDivisor!=0 && rightDivisor!=0) {
	    	int left 	= (int)(x/leftDivisor)%10;
	    	int right 	= x%rightDivisor/(rightDivisor/10);
	    	
	    	if(left!=right)
	    		return false;
	    	
	    	leftDivisor	/= 	10;
	    	rightDivisor*=	10;
	    	
	    	if(leftDivisor == rightDivisor)
	    		break;
    	}
    	
        return true;
    }
    
    public static void printResult(int num) {    	
    	boolean res = isPalindrome(num);
    	System.out.println(num + (res ? " is " : " is NOT ") + "a palindrome!");
    }
    
	public static void main(String[] args) {
		printResult(132);
		printResult(19872);
		printResult(121);
		printResult(1222221);
		printResult(0);
		printResult(7);
		printResult(17);
		printResult(1000000001);
		printResult(1000000003);
	}

}
