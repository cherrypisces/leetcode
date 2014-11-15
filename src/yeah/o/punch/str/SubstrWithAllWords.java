package yeah.o.punch.str;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * http://oj.leetcode.com/problems/substring-with-concatenation-of-all-words/
 * 
 * You are given a string, S, and a list of words, L, that are all of the same length. 
 * Find all starting indices of substring(s) in S that is a concatenation of each word 
 * in L exactly once and without any intervening characters.
 * 
 * 	 For example, given:
 * 		S: "barfoothefoobarman"
 * 		L: ["foo", "bar"]
 * 	 You should return the indices: [0,9].  
 * 	 (order does not matter).
 * 
 */

public class SubstrWithAllWords {
	
	 public static void clearFlags(int[] flags) {
		 if(flags != null) {
			 for(int i=0; i<flags.length; i++) {
				 flags[i] = -1;
			 }
		 }
	 }
	
	 public static ArrayList<Integer> findSubstring3(String S, String[] L) {
		 	if (S == null) return null;

		 	ArrayList<Integer> indices = new ArrayList<Integer>();
		 	if (L==null || L.length==0)	return indices;
		 	
	        final int WordLength = L[0].length();
	        int i = 0;
        	int[] flags = new int[L.length];
        	clearFlags(flags);
        	
        	int start = i;
	        while(i+WordLength<=S.length()) {
	        	String sub = S.substring(i, i+WordLength);
	        	int j;
	        	for(j=0; j<L.length; j++) {
	        		if(sub.equals(L[j]) && flags[j]==-1) {
	        			flags[j] = i;
	        			i = i+WordLength;
	        			break;
	        		} else if (!sub.equals(L[j]) && j==(L.length-1)) {
	        			clearFlags(flags);
	        			i = i + 1;
	        			start = i;
	        			break;
	        		}
	        	}
	        	
	        	boolean isAllConcatenated = true;
	        	for(int t=0; t<flags.length; t++) {
	        		if (flags[t] == -1)
	        			isAllConcatenated = false;
	        	}
	        	
	        	if (isAllConcatenated) {
	        		indices.add(start);
	        		start = i;
	        		clearFlags(flags);
	        	} 
	        	
	        }
	        
	        return indices;
	 }
	 
	 
	 public static ArrayList<Integer> findSubstring(String S, String[] L) {
		 	if (S == null) return null;

		 	ArrayList<Integer> indices = new ArrayList<Integer>();
		 	if (L==null || L.length==0)	return indices;
		 	
	        HashMap<String, Integer> map = new HashMap<String, Integer>();
	        for(int k=0; k<L.length; k++) {
	        	if(map.containsKey(L[k])) {
	        		map.put(L[k], map.get(L[k])+1);
	        	} else {
	        		map.put(L[k], 1);
	        	}
	        }

	        final int WordLength = L[0].length();
	        final int NumOfWords = L.length;
	        int i = 0;
	        String str="";
	        while(i+WordLength*NumOfWords <= S.length()) {
	        	
	        	HashMap<String, Integer> currMap = (HashMap<String, Integer>)map.clone();
	        	
	        	for(int j=0; j < NumOfWords; j++) {
	        		str = S.substring(i+j*WordLength, i+(j+1)*WordLength);
	        		
	        		if (currMap.containsKey(str)) {	        			
	        			int freq = currMap.get(str);
	        			if(freq == 1)
	        				currMap.remove(str);
	        			else 
	        				currMap.put(str,freq-1);
	
	        		} else 
	        			break;
	        	}
	        	
    			if(currMap.isEmpty()) {
    				indices.add(i);
    			}
    			
	        	i++;	        
	        }
	        
	        return indices;
	 }
	 
	 
	 public static void printArray(String[] L) {
		 if(L==null || L.length==0)
			 return;

		 System.out.print("{ ");
		 for(int i=0; i<L.length; i++) {
			 System.out.print(L[i]+" ");
		 }
		 System.out.println("}");
	 }	 
	 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String S="barfoothefoobarman";
		String[] L = {"foo", "bar"};
		System.out.println(S);
		printArray(L);
		ArrayList<Integer> res = findSubstring(S, L);
		System.out.print("[ ");
		for(Integer num : res)
			System.out.print(num + " ");
		System.out.println("]\n");
		
		
		String S2="lingmindraboofooowingdingbarrwingmonkeypoundcake";
		String[] L2={"fooo","barr","wing","ding","wing"};
		System.out.println(S2);
		printArray(L2);
		res = findSubstring(S2, L2);
		System.out.print("[ ");
		for(Integer num : res)
			System.out.print(num + "  ");
		System.out.println("]\n");
		
		
		String S3="abababab";
		String[] L3={"a","b"};
		System.out.println(S3);
		printArray(L3);
		res = findSubstring(S3, L3);
		System.out.print("[ ");
		for(Integer num : res)
			System.out.print(num + "  ");
		System.out.println("]\n");
		
		String S4="aaa";
		String[] L4={"a","a"};
		System.out.println(S4);
		printArray(L4);
		res = findSubstring(S4, L4);
		System.out.print("[ ");
		for(Integer num : res)
			System.out.print(num + "  ");
		System.out.println("]\n");
		
		
		String S5="abababab";
		String[] L5={"a","b","a"};
		System.out.println(S5);
		printArray(L5);
		res = findSubstring(S5, L5);
		System.out.print("[ ");
		for(Integer num : res)
			System.out.print(num + "  ");
		System.out.println("]\n");
		
		
		String S6="a";
		String[] L6={"a"};
		System.out.println(S6);
		printArray(L6);
		res = findSubstring(S6, L6);
		System.out.print("[ ");
		for(Integer num : res)
			System.out.print(num + "  ");
		System.out.println("]\n");
		
		String S7="abababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababababab";
		String[] L7={"ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba","ab","ba"};
		System.out.println(S7);
		printArray(L7);
		res = findSubstring(S7, L7);
		System.out.print("[ ");
		for(Integer num : res)
			System.out.print(num + "  ");
		System.out.println("]\n");
	}

}
