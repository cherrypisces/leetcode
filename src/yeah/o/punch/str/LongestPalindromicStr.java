package yeah.o.punch.str;

/**
 * http://oj.leetcode.com/problems/longest-palindromic-substring/
 * 
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, 
 * and there exists one unique longest palindromic substring.
 * 
 * @author CherryPisces
 *
 */


public class LongestPalindromicStr {

    public static String longestPalindrome(String s) {
        if(s==null || s.length()==0)
        	return "";
        else if(s.length() == 1)
        	return s;
    	
        int maxWinLeft = 0;
        int maxWinRight = 0;
        int maxWinLen = 1;
        int len = s.length();
    	for(int k=1; k<=len-1; k++) {   		
    		int span = (k<(len-k)) ? k : (len-k);
    		
       		// no axis
    		int tmpWin = 0;
    		int dist;
    		for(dist=1; dist <= span; dist++) {
    			if(!(s.charAt(k-dist) == s.charAt(k+dist-1)))
    				break;
    			tmpWin += 2;
    		}
    		
    		dist--;
    		if(tmpWin > maxWinLen) {
    			maxWinLen 	= tmpWin;
    			maxWinLeft  = k - dist;
    			maxWinRight = k + dist - 1;
    			System.out.println("no axis: [" + maxWinLeft + ", " + maxWinRight + "]");
    		}
    		
    		// make s[k] as axis of symmetry
    		tmpWin = 1;
    		span = (k<(len-k-1)) ? k : (len-k-1);
    		for(dist=1; dist <= span; dist++) {
    			if(!(s.charAt(k-dist) == s.charAt(k+dist)))
    				break;
    			tmpWin += 2;
    		}
    		
    		dist--;
    		if(tmpWin > maxWinLen) {
    			maxWinLen 	= tmpWin;
    			maxWinLeft  = k - dist;
    			maxWinRight = k + dist;
    			System.out.println("axis: [" + maxWinLeft + ", " + maxWinRight + "]");
    		}
    		
    	}
    	
    	return s.substring(maxWinLeft, maxWinRight+1);
    }
    
    
    public static void printRes (String s) {
    	String res = longestPalindrome(s);
    	System.out.println(s);
    	System.out.println("Longest Palindromic Substring is: " + res);
    	System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aab";
		printRes(s);
		
		s = "aaaa";
		printRes(s);
		
		s = "bb";
		printRes(s);
	
		s = "abb";
		printRes(s);
		
		s = "abcbe";
		printRes(s);
	}

}
