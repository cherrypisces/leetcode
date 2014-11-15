package yeah.o.punch.str;

import java.util.Stack;

public class ValidParentheses {
	
    public static boolean isValid(String s) {        
    	Stack<Character> stack = new Stack<Character>();
    	
    	int len = s.length();
    	for(int i=0; i<len; i++) {
    		char curr = s.charAt(i);
    		switch (curr) {
    			case '(':
    			case '[':
    			case '{':
    				stack.push(curr);
    				break;
    			case ')':
    				if(stack.isEmpty() || stack.pop() != '(')
    					return false;
    				break;
    			case ']':
    				if(stack.isEmpty() || stack.pop() != '[')
    					return false;
    				break;
    			case '}':
    				if(stack.isEmpty() || stack.pop() != '{')
    					return false;
    				break;
    			default:
    				break;
    		}    				
    	}
    	
    	if(!stack.isEmpty()) {
    		return false;
    	}
    	
    	return true;
    }
    
    public static void printAnswer(String s) {
    	boolean yes = isValid(s);    	
    	if (yes) 
    		System.out.println(s+ " is a valid string!");
    	else
    		System.out.println(s+ " is NOT a valid string!");
    }
    
	public static void main(String[] args) {
		printAnswer("()");
		printAnswer("()[]{}");
		printAnswer("(]");
		printAnswer("([)]");
		printAnswer("(){");
		printAnswer("()]");
		printAnswer("");
		printAnswer(" ");

	}

}
