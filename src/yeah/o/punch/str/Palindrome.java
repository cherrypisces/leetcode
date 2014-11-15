package yeah.o.punch.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Palindrome {
	
	public static boolean isPalindrome(String s, int start, int end) {
		if(s == null || start == end) return true;
		
		int i = start;
		int j = end;
		while (i<j && s.charAt(i)==s.charAt(j)) {
			i++; j--;
		}
		
		if (i>=j) return true;
		else return false;
	}
		
	public static List<List<String>> partition(String s, int start, int end) {
		if (s == null || start >= s.length() || start > end) return null;		

		List<List<String>> partitions = new ArrayList<List<String>>();
	
		for(int k=start; k<=end; k++) {
			if (!isPalindrome(s, start, k)) continue;
			
			if (k==end) {
				ArrayList<String> part = new ArrayList<String>();
				part.add(s.substring(start, end+1));
				
				partitions.add(part);
				break;
			}
			
			List<List<String>> otherParts = partition(s, k+1, end);
			if (otherParts == null || otherParts.size() == 0)
				continue;
			
			for (List<String> p : otherParts) {				
				ArrayList<String> part = new ArrayList<String>();				
				part.add(s.substring(start, k+1));
				part.addAll(p);
				
				partitions.add(part);
			}			
		}
		
		return partitions;
	}
	
	public static List<List<String>> partition(String s) {
		if (s == null) return null;

		return partition(s, 0, s.length()-1);
	}
	
	public static void printAnswer(List<List<String>> partitions, int min) {
		
		if(partitions == null) {
			System.out.println("NULL");
			return;
		} else if (partitions.size() == 0) {
			System.out.println("[]");
			return;
		}		

		System.out.println("[");
		for(List<String> part : partitions) {
			
			System.out.print("   [  ");
			for(String s : part) {
				System.out.print("\"" + s + "\"  ");
			}
			System.out.println("]");

		}
		System.out.println("]");		

		System.out.println("Min Cuts : " + min);
		System.out.println("\n------------------------------------\n");
	}
	
	/////////////////////////////////////////////////////////////////////
	public static int partitionII(String s, int start, int end) {
		if (s == null || start >= s.length() || start > end) return 0;
		else if(isPalindrome(s, start, end)) return 0;

		int minCut = Integer.MAX_VALUE;
	
		for(int k=start; k<=end; k++) {
			if (!isPalindrome(s, start, k)) continue;
			
			if (k==end) {
				minCut = 0;
				break;
			}
			
			List<List<String>> otherParts = partition(s, k+1, end);
			if (otherParts == null || otherParts.size() == 0)
				continue;
			
			for (List<String> p : otherParts) {				
				if(p.size() < minCut)
					minCut = p.size();
			}			
		}
		
		return minCut;
	}	
	
	public static int partitionII(String s) {
		if (s == null) return 0;

		return partitionII(s, 0, s.length()-1);
	}
	////////////////////////////////////////////////////////////////////

	public static int minCut_1_wrong(String s) {
		if (s == null) return 0;

		int len = s.length();
		
		if (isPalindrome(s, 0, len-1))
			return 0;
		
		int[][] min = new int[len][len];
		
		for(int k=0; k<len; k++) 
			min[k][k] = 0;
			
		for(int i=1; i<=len-1; i++) {
			for(int j=0; j<=(len-1)-i; j++) {
				if (isPalindrome(s, j, j+i))
					min[j][j+i] = 0;
				else {
					min[j][j+i] = min[j][j+i-1] < min[j+1][j+i]
								  ? min[j][j+i-1] + 1
							      : min[j+1][j+i] + 1; 
				}
				
				if (j==0 && j+i==513) {
					System.out.println(">>>>>>j==0 && j+i==513>>>>>>>" + min[j][j+i]);
				}
				if (j==514 && j+i==515) {
					System.out.println(">>>>>>j==514 && j+i==515>>>>>>>" + min[j][j+i]);
				}
				if (j==516 && j+i==len-1) {
					System.out.println(">>>>>>j==516 && j+i==len-1>>>>>>>" + min[j][j+i]);
				}
				if (j==0 && j+i==len-2) {
					System.out.println(">>>>>>j==0 && j+i==len-2>>>>>>>" + min[j][j+i]);
				}
				if (j==1 && j+i==len-1) {
					System.out.println(">>>>>>j==1 && j+i==len-1>>>>>>>" + min[j][j+i]);
				}
			}
		}
		
		return min[0][len-1];
	}
	
	/*
	 * let m[i] be the minimum cut needed for s[0...i]
	 * hence 
	 * 	- if s[0...i] is palindrome, m[i] = 0
	 *  - else
	 * 		 m[i] = min(m[j] + 1) if s[j+1, i] is palindrome , (0<=j<i)  
	 * 
	 * O(n^3)
	 *
	 */
	public static int minCut_TLE (String s) {
		if (s == null) return 0;
		
		int len = s.length();
		
		if (len < 2) return 0;
		
		int[] m = new int[len];
		Arrays.fill(m, Integer.MAX_VALUE);
		m[0] = 0;
		
		for (int i=0; i<len; i++) {
			
			if (isPalindrome(s, 0, i)) {
				m[i] = 0;
				continue;
			}
			
			for (int j=0; j<i; j++) {
				if (m[j] + 1 < m[i] 
					&& isPalindrome(s, j+1, i)) {
					m[i] = m[j] + 1;
				}
			}
		}
		
		return m[len-1];
	}
	
	
	/**
	 * optimize time complexity based on above idea
	 * 
	 * s[i...j] is palindrome only if s[i]==s[j] && s[i+1...j-1] is palindrome
	 */
	public static int minCut(String s) {
		if (s==null) return 0;
		
		int len = s.length();
		if (len < 2) return 0;
		
		boolean[][] flags = new boolean[len][len];
		for(int i=0;i<len;i++) {
			
			for(int j=0; j<len-i; j++) {				
				if(s.charAt(j) == s.charAt(j+i) && 
					(i<=1 || flags[j+1][j+i-1]==true))
					flags[j][j+i] = true;
				else
					flags[j][j+i] = false;
			}
		}
		
		int[] m = new int[len];
		Arrays.fill(m, Integer.MAX_VALUE);
		m[0] = 0;
		for(int i=0;i<len;i++) {
			
			if(flags[0][i]) {
				m[i] = 0;
				continue;
			}
			
			for(int j=0; j<i; j++) {
				if(flags[j+1][i] && 
				   m[j] + 1 < m[i]) {
					m[i] = m[j] + 1;
				}
			}
		}
		
		return m[len-1];
	}
	
	////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args) {
		
		String s1 = "aab";
		List<List<String>> a1 = partition(s1);
		int min1 = minCut(s1);
		printAnswer(a1, min1);
		
		String s2 = "a";
		List<List<String>> a2 = partition(s2);
		int min2 = minCut(s2);
		printAnswer(a2, min2);
		
		String s3 = "abbbcd";
		List<List<String>> a3 = partition(s3);
		int min3 = minCut(s3);
		printAnswer(a3, min3);
		
		String s4 = "abcdeffedcba";
		List<List<String>> a4 = partition(s4);
		int min4 = partitionII(s4);
		printAnswer(a4, min4);
		
		String s5 = "ababababababababababababcbabababababababababababa";
	//	List<List<String>> a5 = partition(s5);
		int min5 = minCut(s5);
		System.out.println("Min Cuts: " + min5);
	//	printAnswer(a5, min5);
		
		String s6 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
	//	System.out.println("b index: " + s6.indexOf('b'));
		int min6 = minCut(s6);
		System.out.println("Min Cuts: " + min6);
		
		String s7 = "aabbaaaa";
		int min7 = minCut(s7);
		System.out.println("Min Cuts: " + min7);
		
		String s8 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabbaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		if (isPalindrome(s8, 0, s8.length()-1)) {
			System.out.println("YES");
		}
		int min8 = minCut(s8);
		System.out.println("Min Cuts: " + min8);
		
		boolean[][] test= new boolean[2][2];
		for(int i=0;i<2;i++) {
			System.out.print("[");
			for(int j=0;j<2;j++) {
				System.out.print(test[i][j] + "  ");
			}
			System.out.println("]");
		}
		
		
		ArrayList<Integer> array = new ArrayList<Integer>();
	}

}
