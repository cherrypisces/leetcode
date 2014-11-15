package yeah.o.punch.str;

import java.util.HashMap;

/**
 * 
 * http://oj.leetcode.com/problems/longest-substring-without-repeating-characters/
 * 
 * Given a string, find the length of the longest substring without repeating characters. 
 * 
 * For example, 
 * 	   the longest substr without repeating letters for "abcabcbb" is "abc", the length is 3. 
 * 	   For "bbbbb" the longest substring is "b", with the length of 1.
 * 
 * @author CherryPisces
 *
 */


public class LongestSubStrNoRepeat {

	public static int lengthOfLongestSubstring(String s) {
		if (s==null || s.length()==0)
			return 0;
		
	    int max = 1;
	    String sb = "";
	    for(int i=0; i<s.length(); i++) {
	    
	    	char c = s.charAt(i);
	    	if (sb.indexOf(c) == -1) {
	    		sb += c;
	    		
	    		if(sb.length() > max) {
	    			max = sb.length();
	    		}
	    	} 
	    	else {
	    		int pos = sb.indexOf(c); 
	    		sb = sb.substring(pos+1, sb.length());
	    		sb += c;
	    	}
	    }

	    return max;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "abcabcbb";
		int res = lengthOfLongestSubstring(s1);
		System.out.println(s1 + " : " + res);
		System.out.println("--------------------------------");

		String s2 = "bbbbb";
		res = lengthOfLongestSubstring(s2);
		System.out.println(s2 + " : " + res);
		System.out.println("--------------------------------");
		
		String s3 = "qopubjguxhxdipfzwswybgfylqvjzhar";
		res = lengthOfLongestSubstring(s3);
		System.out.println(s3 + " : " + res);
		System.out.println("--------------------------------");
	}

}
