package yeah.o.punch.str;

public class ValidPalindrome {
	private static boolean isAlphabet(char c) {
		return (c>='a' && c<='z') || (c>='A' && c<='Z') || (c>='0' && c<='9');
	}
	
	private static boolean equalsIgnoreCase(char a, char b) {
		return (a==b) || (a==b+('a'-'A')) || (a+('a'-'A')==b);
	}
	
    public static boolean isPalindrome(String s) {
        if(s==null || s=="")
        	return true;
        
        int LEN = s.length();
        int left = 0;
        int right = LEN-1;
        
        while(left <= right) {
        	while (left<LEN && !isAlphabet(s.charAt(left))) 
        		left++;
        	
        	while (right>=0 && !isAlphabet(s.charAt(right))) 
        		right--;
        	
        	if (left > right)
        		break;
        	
        	if(!equalsIgnoreCase(s.charAt(left), s.charAt(right)))
        		return false;
        	
        	left++;
        	right--;
        }
        
        return true;
    }
	
	
	public static void main(String[] args) {
		String s1 = "A man, a plan, a canal: Panama";
		String res1 = isPalindrome(s1) ? " is " : " is NOT "; 
		System.out.println("[" + s1 + "]" + res1 +"Valid Palindrome");
		String s2 = "race a car";
		String res2 = isPalindrome(s2) ? " is " : " is NOT "; 
		System.out.println("[" + s2 + "]" + res2 +"Valid Palindrome");
		String s3 = " ";
		String res3 = isPalindrome(s3) ? " is " : "is NOT "; 
		System.out.println("[" + s3 + "]" + res3 +"Valid Palindrome");
		String s4 = "1a2";
		String res4 = isPalindrome(s4) ? " is " : " is NOT "; 
		System.out.println("[" + s4 + "]" + res4 +"Valid Palindrome");
	}

}
