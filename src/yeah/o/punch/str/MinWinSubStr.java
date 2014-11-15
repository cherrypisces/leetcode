package yeah.o.punch.str;

import java.util.HashMap;

/**
 * 
 * http://oj.leetcode.com/problems/minimum-window-substring/
 * 
 * Given a string S and a string T, find the minimum window in S 
 * which will contain all the characters in T in complexity O(n).
 * 		For example,
 * 			S = "ADOBECODEBANC"		T = "ABC"
 * 			Minimum window is "BANC".
 * 
 * Note:
 * If there is no such window in S that covers all characters in T, return the emtpy string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 * 
 * @author CherryPisces
 * 
 * [Thought from]
 * http://leetcode.com/2010/11/finding-minimum-window-in-s-which.html
 *
 */

public class MinWinSubStr {

	public static String minWindow(String S, String T) {
		if (S==null || S.length()==0 || T==null || T.length()==0)
			return "";
		
		int tLen = T.length();
		int[] expect = new int[256];
		for(int i=0; i<tLen; i++) {
			expect[T.charAt(i)]++;
		}
		
	    int[] found = new int[256];
	    int minWindowBegin = 0;
	    int minWindowEnd = 0;
	    int minWindowLen = Integer.MAX_VALUE;
		int sLen = S.length();
		int count = 0;
	    for(int begin=0, end=0; end<sLen; end++) {	    	
	    	char endChar = S.charAt(end);
	    	if (expect[endChar] == 0) 
	    		continue;
	    	
	    	found[endChar]++;
	    	if (found[endChar] <= expect[endChar]) 
	    		count++;
	    	
	    	if (count == tLen) {	    		
	    		while(expect[S.charAt(begin)] == 0 || 
	    				found[S.charAt(begin)] > expect[S.charAt(begin)]) {
	    			
	    			if(found[S.charAt(begin)] > expect[S.charAt(begin)])
	    				found[S.charAt(begin)]--;	    			
	    			begin++;
	    		}
	    		
	    		int winLen = end - begin + 1;
	    		if(winLen < minWindowLen) {
	    			minWindowLen = winLen;
	    			minWindowBegin = begin;
	    			minWindowEnd = end;
	    		}
	    	}
	    }
		
	    if (count == tLen)
	    	return S.substring(minWindowBegin, minWindowEnd+1);
	    else 
	    	return "";
	}
	
	public static void printResult(String S, String T, String answer) {
		System.out.println("S: " + S);
		System.out.println("T: " + T);
		String res = minWindow(S, T);
		System.out.println("MinWin: " + res);
		if (!res.equals(answer))
			System.out.println("!Wrong Answer! Correct is: " + answer);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printResult("ADOBECODEBANC", "ABC", "BANC");
		printResult("cabeca", "cae", "eca");
		printResult("cfabeca", "cae", "eca");
		printResult("cabefgecdaecf", "cae", "aec");
		printResult("cabwefgewcwaefcf", "cae", "cwae");
		printResult("abcabdebac", "cda", "cabd");
		printResult("abcabdebac", "cea", "ebac");
		printResult("acbdbaab", "aabd", "dbaa");
		printResult("caaec", "cae", "aec");
		printResult("caae", "cae", "caae");
		printResult("acbbaab", "aab", "baa");
		printResult("acbba", "aab", "acbba");
		printResult("adobecodebanc", "abc", "banc");
		printResult("adobecodebanc", "abcda", "adobecodeba");
		printResult("adobecodebanc", "abdbac", "adobecodeba");
		printResult("adobecodebancbbcaa", "abc", "bca");
		printResult("aaaaaaaaaaaaaaa", "a", "a");
		printResult("aaaaaaaaaaaaaaa", "aaaaaaaaaaaaaa", "aaaaaaaaaaaaaa");
		printResult("acccabeb", "ab", "ab");
		printResult("aaabdacefaecbef", "abc", "bdac");
		printResult("coobdafceeaxab", "abc", "bdafc");
		printResult("of_characters_and_as", "aas", "and_as");
		printResult("a", "a", "a");
		printResult("a", "a", "a");
		printResult("a", "b", "");
		printResult("aa", "a", "a");
		printResult("aaa", "aaa", "aaa");
		printResult("aab", "aab", "aab");
	
	}

}
