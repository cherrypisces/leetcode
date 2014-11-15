package yeah.o.punch.str;

import java.util.ArrayList;

public class ReverseWords {
	
    public static String reverseWords2(String s) {
        ArrayList<String> list = new ArrayList<String>();
        
    	for(int i=0; i<s.length(); i++) {
    		StringBuilder sb = new StringBuilder();
    		
    		while(i < s.length() && s.charAt(i) != ' ' && s.charAt(i) != '\t') {
    			sb.append(s.charAt(i));
    			i++;
    		}
    		
    		if(sb.length()>0)
    			list.add(sb.toString());
    	}
    	
    	StringBuilder res = new StringBuilder();
    	for(int j=list.size()-1; j > 0; j--) {
    		res.append(list.get(j)).append(' ');
    	}
    	if(list.size() > 0)
    		res.append(list.get(0));
    	
    	return res.toString();
    }
	
    
    public static String reverseWords(String s) {
    	StringBuilder res = new StringBuilder();
    	
    	for(int i=0; i<s.length(); i++) {    		
    		StringBuilder sb = new StringBuilder();    		
    		while(i < s.length() && s.charAt(i) != ' ' && s.charAt(i) != '\t') {
    			sb.append(s.charAt(i));
    			i++;
    		}
    		
    		if(sb.length()>0) {
    			res.insert(0, sb);
    			res.insert(0,  ' ');
    		}
    	}
    	
    	if(res.length() > 0)
    		res.deleteCharAt(0);
    	
    	return res.toString();
    }
    
    
	public static void main(String[] args) {
		String input = "a  im  sdfsdf ";
		String res = reverseWords(input);
		System.out.println(res);
	}

}
