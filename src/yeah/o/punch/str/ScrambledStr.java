package yeah.o.punch.str;

/*
 * http://oj.leetcode.com/problems/scramble-string/
 * 
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * 
 * Below is one possible representation of s1 = "great":
 * 		  great
 * 		  /    \
 * 		 gr    eat
 * 	    / \    /  \
 * 	   g   r  e   at
 * 				  / \
 * 				 a   t
 * 
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 * 
 * 		  rgeat
 * 		  /    \
 * 		 rg    eat
 * 		/ \    /  \
 * 		r  g  e   at
 * 				  / \
 * 				 a   t
 * 
 * We say that "rgeat" is a scrambled string of "great".
 * 
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 * 
 * 		  rgtae
 * 		 /    \
 * 		rg    tae
 * 		/ \   /  \
 * 	   r   g  ta  e
 * 			  / \
 * 			 t   a
 * 
 * We say that "rgtae" is a scrambled string of "great".
 * 
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1. 
 * 
 */


public class ScrambledStr {

    public static boolean isScrambleRecur(String s1, String s2) {
        if(s1.length() != s2.length())
        	return false;
        else if(s1.length()==1 && s1.equals(s2))
        	return true;
        else if(s1.length()==2 && (s1.equals(s2) || 
        		(s1.charAt(0)==s2.charAt(1) && s1.charAt(1)==s2.charAt(0))))
        	return true;
        else if(s1.equals(s2))
        	return true;
        else {
        	int[] counter = new int[26];
        	for(int i=0; i<s1.length(); i++) {
        		counter[s1.charAt(i)-'a']++;
        	}
        	for(int i=0; i<s2.length(); i++) {
        		counter[s2.charAt(i)-'a']--;
        	}
        	for(int i=0; i<26; i++) {
        		if(counter[i] != 0)
        			return false;
        	}
        }
  
        for(int len=1; len<s1.length(); len++) {
        	String s11 = s1.substring(0,len);
        	String s12 = s1.substring(len);
        	String s21 = s2.substring(0,len);
        	String s22 = s2.substring(len);
        	
        	if (isScrambleRecur(s11, s21) && isScrambleRecur(s12, s22)) 
        		return true;
        	
        	s21 = s2.substring(0,s1.length()-len);
        	s22 = s2.substring(s1.length()-len, s1.length());
        	
        	if (isScrambleRecur(s11, s22) && isScrambleRecur(s12, s21))
        		return true;
        }
        
        return false;
    }
    
    
    /*
     * http://www.cnblogs.com/TenosDoIt/p/3452004.html
     * 
     */
    public static boolean isScrambleDP(String s1, String s2) {
    	  if(s1.length() != s2.length())
    		  return false;
    	  
    	  if(s1.equals(s2))
    		  return true;
    	  
    	  int len = s1.length();
	      int[] counter = new int[26];
	      for(int i=0; i<len; i++) {
	    	  counter[s1.charAt(i)-'a']++;
	      }
	      for(int i=0; i<len; i++) {
	    	  counter[s2.charAt(i)-'a']--;
	      }
	      for(int i=0; i<26; i++) {
	    	  if(counter[i] != 0)
	    		  return false;
	      }
	      
	      boolean[][][] res = new boolean[len][len][len+1];
	      for(int i=0; i<len; i++)
	    	  for(int j=0; j<len; j++)
	    		  res[i][j][1] = ( s1.charAt(i) == s2.charAt(j) );
	    
		  for(int k=2; k<=len; k++) {
			  for(int i=0; i<=len-k; i++)
				  for(int j=0; j<=len-k; j++) {
		    		  res[i][j][k] = false;
		    		  for(int dist=1; dist<k && !res[i][j][k]; dist++) {
		    			  res[i][j][k] = (res[i][j][dist] && res[i+dist][j+dist][k-dist]) || 
		    			  				 (res[i][j+k-dist][dist] && res[i+dist][j][k-dist]);
		    		  }
	    		  }
	    	  }
	      
	      return res[0][0][len];
    } 
    
    
	public static void printTestCase(String s1, String s2) {
		System.out.println("------------------------------------------------------");
		boolean res = isScrambleDP(s1, s2);
		System.out.print(s2);
		System.out.print(res ? " IS " : " IS NOT ");
		System.out.println("scrambled string of " + s1);
		System.out.println();
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "great";
		String s2 = "rgtae";
		printTestCase(s1, s2);
		
		s1="abcdefghijklmnopq";
		s2="efghijklmnopqcadb";
		printTestCase(s1, s2);

		s1="abcd";
		s2="bdac";
		printTestCase(s1, s2);
	}

}
