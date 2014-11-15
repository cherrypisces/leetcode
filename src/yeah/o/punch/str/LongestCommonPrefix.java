package yeah.o.punch.str;

public class LongestCommonPrefix {
	
    public static String longestCommonPrefix(String[] strs) {
    	if (strs==null) 
    		return null;
    	else if (strs.length==0)
    		return "";
    	
        StringBuilder prefix = new StringBuilder();
    	int len = strs.length;
        int k=0;
        while(true) {
        	for(int i=0; i<len-1; i++) {
        		if(k>=strs[i].length() || k>=strs[i+1].length())
        			return prefix.toString();
        		
        		char c	=strs[i].charAt(k);
        		char cc	=strs[i+1].charAt(k);
        		if (c != cc)
        			return prefix.toString();
        	}
        	
        	if(strs[0].length()>0 && k<strs[0].length())
        		prefix.append(strs[0].charAt(k));
        	else
        		return prefix.toString();
        	k++;
        }
    }
    
	public static void main(String[] args) {
		String[] strs= {
				"abcdefg",
				"abcef",
				"a"
		};
		String res = longestCommonPrefix(strs);
		System.out.println(res);
		
		String[] strs2=new String[0];
		String res2 = longestCommonPrefix(strs2);
		System.out.println(res2);		

		String[] strs3=new String[]{""};
		String res3 = longestCommonPrefix(strs3);
		System.out.println(res3);
		
		String[] strs4=new String[]{"a"};
		String res4 = longestCommonPrefix(strs4);
		System.out.println(res4);
	}

}
