package yeah.o.punch.str;

public class LengthOfLastWord {
	
    public static int lengthOfLastWord(String s) {
        if(s==null || s.length()==0) return 0;
        
        int len = s.length();
        int i = len-1 ;
        while (i>=0) {
        	while(i>=0 && s.charAt(i) == ' ') i--;
        	
        	if(i>=0) {
        		int count = 0;
        		while(i>=0 && s.charAt(i) != ' ') {
        			count++;
        			i--;
        		}
        		return count;
        	}
        }
        
        return 0;
    }
    
	public static void main(String[] args) {
		String s1 = "abcd   dddd";
		int len1 = lengthOfLastWord(s1);
		System.out.println(len1);
		
		String s2 = "abcd   d  ";
		int len2 = lengthOfLastWord(s2);
		System.out.println(len2);
		
		String s3 = "  abcd   d";
		int len3 = lengthOfLastWord(s3);
		System.out.println(len3);
		
		String s4 = "  a   ";
		int len4 = lengthOfLastWord(s4);
		System.out.println(len4);
		
		String s5 = "77   ";
		int len5 = lengthOfLastWord(s5);
		System.out.println(len5);
		
		String s6 = "    ";
		int len6 = lengthOfLastWord(s6);
		System.out.println(len6);
	}

}
