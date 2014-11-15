package yeah.o.punch.str;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
	
	/**
	 * Dynamic Programming 
	 *
	 * Initially, we have
	 * boolean flag[s.length + 1], all default to be false
	 * flag[0] = true  
	 * 
	 * flag[i] = true : means s[0...i] satisfy the requirement
	 * so our final answer is flag[s.length]
	 * 
	 * sub problem is flag[i], it will be true only if we can find a j (0<j<=i) 
	 * i)  flag[j]=true
	 * ii) s.substr(j+1,i) can be found in dict
	 * 
	 */
    public static boolean wordBreakI(String s, Set<String> dict) {
    	int len = s.length();
        boolean[] flags = new boolean[len+1]; 
    	flags[0] = true;
    	
    	for(int i=1; i<=len; i++) {
    		for(int j=0; j<i; j++) {
    			
    			if(flags[j] && dict.contains(s.substring(j, i))) {
    				flags[i] = true;
    				break;
    			}
    		}
    	}
     	
    	return flags[len];
    }
    
    public static List<String> wordBreakII(String s, Set<String> dict) {
    	if(wordBreakI(s, dict))
    		return solution(s, dict, 0, s.length()-1);
    	else
    		return new ArrayList<String>();
    }
    
    private static List<String> solution(String s, Set<String> dict, int i, int j) {
    	if(i > j)
    		return null;
    	
    	List<String> all = new ArrayList<String>();
    	
    	for(int k=i; k<=j; k++) {
    		String substr = s.substring(i, k+1);
    		if (dict.contains(substr)) {
    			List<String> subList = solution(s, dict, k+1, j);
    			
    			if (subList != null && !subList.isEmpty()) {    				
    				for(String str : subList) {
    					all.remove(str);
    					all.add(substr + " " + str);
    				}
    			} else if (k==s.length()-1) {
    				all.add(substr);
    			}
    		}
    	}
    	
    	return all;
    }
        
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    static class IdxPair {
    	int from;
    	int to;
    	
    	public IdxPair(int f, int t) 
    	{
    		from = f;
    		to 	 = t;	
    	}
    }
    
    public static List<String> wordBreakII2(String s, Set<String> dict) {
    	
    	ArrayList<IdxPair> indexes = new ArrayList<IdxPair>();
    	
    	int len = s.length();
        boolean[] flags = new boolean[len+1]; 
    	flags[0] = true;
    	for(int i=1; i<=len; i++) {
    		for(int j=0; j<i; j++) {
    			
    			if(flags[j] && dict.contains(s.substring(j, i))) {
    				flags[i] = true;
    				if((i==len) || (i<len && wordBreakI(s.substring(i, len), dict)))
    					indexes.add(new IdxPair(j,i-1));
    			}
    		}
    	}
     	
    	List<String> all = new ArrayList<String>();
    	
    	if(flags[len] == false)
    		return all;
    	
    	int indexLen = indexes.size();
    	for(int i=0; i<indexLen; i++) {
    		IdxPair curr = indexes.get(i);
    		
    		if(curr.from == 0) {
    			String sentence = s.substring(curr.from, curr.to+1);
    			
				int next = curr.to+1;
				int j = i+1;
				while((j<indexLen) && (next<len)) {
					IdxPair candidate = indexes.get(j);
					
					if(candidate.from == next) {
						curr = candidate;
						sentence += " " + s.substring(curr.from, curr.to+1); 
						next = curr.to+1;
						if(next == len)
							break;
					}
					j++;
				}
				
				if (next == len) {
					all.add(sentence);
				}				
    		}
    	}
    	
    	return all;
    } 
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
	public static void main(String[] args) {
	/*	String s1 = "leetcode";
		Set<String> dict1 = new HashSet<String>(Arrays.asList(new String[]{"leet","code"}));
		boolean flag = wordBreakI(s1, dict1);
		System.out.println(flag ? "Yes" : "No");
		
		String s2 = "hellobingo";
		Set<String> dict2 = new HashSet<String>(Arrays.asList(new String[]{"hello","bing"}));
		flag = wordBreakI(s2, dict2);
		System.out.println(flag ? "Yes" : "No"); 
				
		String s3 = "catsanddog";
		Set<String> dict3 = new HashSet<String>(Arrays.asList(new String[]{"cat","cats","and","sand","dog"}));
		List<String> result3 = wordBreakII2(s3, dict3);
		for(String s : result3) {
			System.out.println(s);
		}
		
		System.out.println("result3 ==========================");
		
		String s4 = "aaaaaaaaaaaaaaaaaaaaaab";
		Set<String> dict4 = new HashSet<String>(Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa"}));
		List<String> result4 = wordBreakII2(s4, dict4);
			for(String s : result4) {
				System.out.println(s);
		}		

		System.out.println("result4 ==========================");
		
		String s5 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
		Set<String> dict5 = new HashSet<String>(Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"}));
		List<String> result5 = wordBreakII2(s5, dict5);
			for(String s : result5) {
				System.out.println(s);
		} 
			
		System.out.println("result5 ==========================");
		
		String s6 = "a";
		Set<String> dict6 = new HashSet<String>(Arrays.asList(new String[]{}));
		List<String> result6 = wordBreakII2(s6, dict6);
			for(String s : result6) {
				System.out.println(s);
		}		

		System.out.println("result6 ==========================");
		
		
		String s7 = "aaaaaaa";
		Set<String> dict7 = new HashSet<String>(Arrays.asList(new String[]{"aaaa","aaa"}));
		List<String> result7 = wordBreakII2(s7, dict7);
			for(String s : result7) {
				System.out.println(s);
		}		

		System.out.println("result7 ==========================");
		*/
		String s8 = "aaaaaaa";
		Set<String> dict8 = new HashSet<String>(Arrays.asList(new String[]{"aaaa","aa","a"}));
		List<String> result8 = wordBreakII2(s8, dict8);
			for(String s : result8) {
				System.out.println(s);
		}		

		System.out.println("result7 ==========================");
		
	}

}
